package com.example.demoanalytic;


import java.io.Serializable;
import java.util.List;

public class BrandConnectBean {
    /**
     * code : 200
     * data : {"rooms":"次卧,厨房,阳台,洗手间,工作间","rcRooms":"厨房","list":[{"keyIndex":0,"keyName":"开"},{"keyIndex":1,"keyName":"关"},{"keyIndex":2,"keyName":"摆风"},{"keyIndex":3,"keyName":"风速"},{"keyIndex":4,"keyName":"风类"},{"keyIndex":5,"keyName":"静音"}],"entity":{"equipmentNote":"红外遥控","device_id":"5","kfid":"50000","userId":"misnApp125106","productId":"zcz004","infraredBinId":0,"equipmentId":"zcz004100411","mac":"df3c66db8eded811","brand_id":"1119","nick":"美的电风扇","equipmentState":2,"rcRoom":"客厅","rcOperateCode":"05,00,00,00,01,01","modeId":"6a56dfd96d1657882000851","modeHead":"013D,12","rcCreateTime":"1658383396777"}}
     * message : 操作成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        /**
         * rooms : 次卧,厨房,阳台,洗手间,工作间
         * rcRooms : 厨房
         * list : [{"keyIndex":0,"keyName":"开"},{"keyIndex":1,"keyName":"关"},{"keyIndex":2,"keyName":"摆风"},{"keyIndex":3,"keyName":"风速"},{"keyIndex":4,"keyName":"风类"},{"keyIndex":5,"keyName":"静音"}]
         * entity : {"equipmentNote":"红外遥控","device_id":"5","kfid":"50000","userId":"minsApp125106","productId":"zcz004","infraredBinId":0,"equipmentId":"zcz004100411","mac":"df3c66db8eded811","brand_id":"1119","nick":"美的电风扇","equipmentState":2,"rcRoom":"客厅","rcOperateCode":"05,00,00,00,01,01","modeId":"6a56dfd96d1657882000851","modeHead":"013D,12","rcCreateTime":"1658383396777"}
         */

        private String rooms;
        private String rcRooms;
        private EntityBean entity;
        private List<ListBean> list;

        public String getRooms() {
            return rooms;
        }

        public void setRooms(String rooms) {
            this.rooms = rooms;
        }

        public String getRcRooms() {
            return rcRooms;
        }

        public void setRcRooms(String rcRooms) {
            this.rcRooms = rcRooms;
        }

        public EntityBean getEntity() {
            return entity;
        }

        public void setEntity(EntityBean entity) {
            this.entity = entity;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class EntityBean implements Serializable {
            /**
             * equipmentNote : 红外遥控
             * device_id : 5
             * kfid : 50000
             * userId : minAapp108881
             * productId : zcz004
             * infraredBinId : 0
             * equipmentId : zcz004100411
             * mac : df3c66db8eded811
             * brand_id : 1119
             * nick : 美的电风扇
             * equipmentState : 2
             * rcRoom : 客厅
             * rcOperateCode : 05,00,00,00,01,01
             * modeId : 6a56dfd96d1657882000851
             * modeHead : 013D,12
             * rcCreateTime : 1658383396777
             */

            private String equipmentNote;
            private String device_id;
            private String kfid;
            private String userId;
            private String productId;
            private int infraredBinId;
            private String equipmentId;
            private String mac;
            private String brand_id;
            private String nick;
            private int equipmentState;
            private String rcRoom;
            private String rcOperateCode;
            private String modeId;
            private String modeHead;
            private String rcCreateTime;

            public String getEquipmentNote() {
                return equipmentNote;
            }

            public void setEquipmentNote(String equipmentNote) {
                this.equipmentNote = equipmentNote;
            }

            public String getDeviceId() {
                return device_id;
            }

            public void setDevice_id(String device_id) {
                this.device_id = device_id;
            }

            public String getKfid() {
                return kfid;
            }

            public void setKfid(String kfid) {
                this.kfid = kfid;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public int getInfraredBinId() {
                return infraredBinId;
            }

            public void setInfraredBinId(int infraredBinId) {
                this.infraredBinId = infraredBinId;
            }

            public String getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(String equipmentId) {
                this.equipmentId = equipmentId;
            }

            public String getMac() {
                return mac;
            }

            public void setMac(String mac) {
                this.mac = mac;
            }

            public String getBrandId() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public int getEquipmentState() {
                return equipmentState;
            }

            public void setEquipmentState(int equipmentState) {
                this.equipmentState = equipmentState;
            }

            public String getRcRoom() {
                return rcRoom;
            }

            public void setRcRoom(String rcRoom) {
                this.rcRoom = rcRoom;
            }

            public String getRcOperateCode() {
                return rcOperateCode;
            }

            public void setRcOperateCode(String rcOperateCode) {
                this.rcOperateCode = rcOperateCode;
            }

            public String getModeId() {
                return modeId;
            }

            public void setModeId(String modeId) {
                this.modeId = modeId;
            }

            public String getModeHead() {
                return modeHead;
            }

            public void setModeHead(String modeHead) {
                this.modeHead = modeHead;
            }

            public String getRcCreateTime() {
                return rcCreateTime;
            }

            public void setRcCreateTime(String rcCreateTime) {
                this.rcCreateTime = rcCreateTime;
            }
        }

        public static class ListBean implements Serializable {
            /**
             * keyIndex : 0
             * keyName : 开
             */

            private int keyIndex;
            private String keyName;

            public int getKeyIndex() {
                return keyIndex;
            }

            public void setKeyIndex(int keyIndex) {
                this.keyIndex = keyIndex;
            }

            public String getKeyName() {
                return keyName;
            }

            public void setKeyName(String keyName) {
                this.keyName = keyName;
            }
        }
    }
}
