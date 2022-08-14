package main

import (
	"encoding/json"
	"io/ioutil"
	"log"
	"math/rand"
	"net/http"
	"time"
)

type BMSMasterData struct {
	Timestamp int64           `json: "timestamp"`
	Packets   []BMSPacketData `json: "packets"`
}

type BMSPacketData struct {
	Id  string  `json: "id"`
	Soc float32 `json: "soc"`
}

var (
	batteryIds []string
)

func pickRandomBatteryIds(number int) []string {
	if number < 1 || batteryIds == nil {
		return []string{}
	}
	randomBatteryIds := make([]string, number)
	for i := 0; i < number; i++ {
		randomBatteryId := batteryIds[rand.Intn(len(batteryIds))]
		randomBatteryIds[i] = randomBatteryId
	}
	return randomBatteryIds
}

func generateRandomPacket(ids []string) BMSMasterData {
	if len(ids) == 0 || ids == nil {
		ids = pickRandomBatteryIds(3)
	}
	bmsPacketDatas := make([]BMSPacketData, len(ids))
	for i := 0; i < len(ids); i++ {
		bmsPacketData := &BMSPacketData{Id: ids[i], Soc: rand.Float32() * 100}
		bmsPacketDatas[i] = *bmsPacketData
	}
	return BMSMasterData{Timestamp: time.Now().UnixMilli(), Packets: bmsPacketDatas}
}

func main() {
	rand.Seed(time.Now().UnixMilli())
	response, err := http.Get(`http://localhost:8080/battery/ids`)
	if err != nil {
		log.Fatal("error", err)
	}
	bytes, err := ioutil.ReadAll(response.Body)
	defer func() {
		e := response.Body.Close()
		if e != nil {
			log.Fatal(e)
		}
	}()
	if err != nil {
		log.Fatal("readAllError", err)
	}

	err = json.Unmarshal(bytes, &batteryIds)
	if err != nil {
		log.Fatal(err)
	}
	log.Println(generateRandomPacket(pickRandomBatteryIds(500)))
}
