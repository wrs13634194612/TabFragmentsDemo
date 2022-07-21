package com.example.demoanalytic;


import android.text.TextUtils;
import android.view.View;

import java.io.Serializable;
import java.util.List;

public class ClodAirBean {
    /**
     * code : 200
     * data : {"rooms":"客厅,主卧,次卧,厨房,阳台,洗手间,工作间","modes":[{"delays":{"id":54,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","executeTime":"00:02","executeTimePoint":"17:43:50","switchStatus":0,"createTime":"1658223675812"},"timings":{"id":12,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","week":"0","startTime":"17:34","endTime":"17:33","openIf":2,"closeIf":2,"createTime":"1658223073867"},"list":[{"keyIndex":0,"keyName":"开"},{"keyIndex":1,"keyName":"关"},{"keyIndex":2,"keyName":"自动"},{"keyIndex":3,"keyName":"制冷"},{"keyIndex":4,"keyName":"除湿"},{"keyIndex":5,"keyName":"送风"},{"keyIndex":6,"keyName":"制热"},{"keyIndex":7,"keyName":"温度+"},{"keyIndex":8,"keyName":"温度-"},{"keyIndex":9,"keyName":"风速+"},{"keyIndex":10,"keyName":"风速-"},{"keyIndex":11,"keyName":"上下扫风"},{"keyIndex":12,"keyName":"左右扫风"}],"entity":{"equipmentNote":"红外遥控","device_id":"1","kfid":"10122","userId":"minApp108881","productId":"zcz004","infraredBinId":25,"equipmentId":"zcz004100411","mac":"df3c66db8eded811","brand_id":"3","nick":"美的空调","equipmentState":2,"rcRoom":"客厅","rcOperateCode":"01,00,00,01,02,21,00,00","modeId":"452f2959e61658142200308","modeHead":"025F,1F","rcCreateTime":"1658223016440"}}],"rcSelectRooms":"客厅,厨房"}
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
         * rooms : 客厅,主卧,次卧,厨房,阳台,洗手间,工作间
         * modes : [{"delays":{"id":54,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","executeTime":"00:02","executeTimePoint":"17:43:50","switchStatus":0,"createTime":"1658223675812"},"timings":{"id":12,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","week":"0","startTime":"17:34","endTime":"17:33","openIf":2,"closeIf":2,"createTime":"1658223073867"},"list":[{"keyIndex":0,"keyName":"开"},{"keyIndex":1,"keyName":"关"},{"keyIndex":2,"keyName":"自动"},{"keyIndex":3,"keyName":"制冷"},{"keyIndex":4,"keyName":"除湿"},{"keyIndex":5,"keyName":"送风"},{"keyIndex":6,"keyName":"制热"},{"keyIndex":7,"keyName":"温度+"},{"keyIndex":8,"keyName":"温度-"},{"keyIndex":9,"keyName":"风速+"},{"keyIndex":10,"keyName":"风速-"},{"keyIndex":11,"keyName":"上下扫风"},{"keyIndex":12,"keyName":"左右扫风"}],"entity":{"equipmentNote":"红外遥控","device_id":"1","kfid":"10122","userId":"minApp108881","productId":"zcz004","infraredBinId":25,"equipmentId":"zcz004100411","mac":"df3c66db8eded811","brand_id":"3","nick":"美的空调","equipmentState":2,"rcRoom":"客厅","rcOperateCode":"01,00,00,01,02,21,00,00","modeId":"452f2959e61658142200308","modeHead":"025F,1F","rcCreateTime":"1658223016440"}}]
         * rcSelectRooms : 客厅,厨房
         */

        private String rooms;
        private String rcSelectRooms;
        private List<ModesBean> modes;

        public String getRooms() {
            return rooms;
        }

        public void setRooms(String rooms) {
            this.rooms = rooms;
        }

        public String getRcSelectRooms() {
            return rcSelectRooms;
        }

        public void setRcSelectRooms(String rcSelectRooms) {
            this.rcSelectRooms = rcSelectRooms;
        }

        public List<ModesBean> getModes() {
            return modes;
        }

        public void setModes(List<ModesBean> modes) {
            this.modes = modes;
        }

        public static class ModesBean implements Serializable {
            /**
             * delays : {"id":54,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","executeTime":"00:02","executeTimePoint":"17:43:50","switchStatus":0,"createTime":"1658223675812"}
             * timings : {"id":12,"brandId":"3","modeId":"452f2959e61658142200308","productId":"zcz004","equipmentId":"zcz004100411","week":"0","startTime":"17:34","endTime":"17:33","openIf":2,"closeIf":2,"createTime":"1658223073867"}
             * list : [{"keyIndex":0,"keyName":"开"},{"keyIndex":1,"keyName":"关"},{"keyIndex":2,"keyName":"自动"},{"keyIndex":3,"keyName":"制冷"},{"keyIndex":4,"keyName":"除湿"},{"keyIndex":5,"keyName":"送风"},{"keyIndex":6,"keyName":"制热"},{"keyIndex":7,"keyName":"温度+"},{"keyIndex":8,"keyName":"温度-"},{"keyIndex":9,"keyName":"风速+"},{"keyIndex":10,"keyName":"风速-"},{"keyIndex":11,"keyName":"上下扫风"},{"keyIndex":12,"keyName":"左右扫风"}]
             * entity : {"equipmentNote":"红外遥控","device_id":"1","kfid":"10122","userId":"minApp108881","productId":"zcz004","infraredBinId":25,"equipmentId":"zcz004100411","mac":"df3c66db8eded811","brand_id":"3","nick":"美的空调","equipmentState":2,"rcRoom":"客厅","rcOperateCode":"01,00,00,01,02,21,00,00","modeId":"452f2959e61658142200308","modeHead":"025F,1F","rcCreateTime":"1658223016440"}
             */

            private DelaysBean delays;
            private TimingsBean timings;
            private EntityBean entity;
            private List<ListBean> list;

            public DelaysBean getDelays() {
                return delays;
            }

            public void setDelays(DelaysBean delays) {
                this.delays = delays;
            }

            public TimingsBean getTimings() {
                return timings;
            }

            public void setTimings(TimingsBean timings) {
                this.timings = timings;
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

            public static class DelaysBean implements Serializable {
                /**
                 * id : 54
                 * brandId : 3
                 * modeId : 452f2959e61658142200308
                 * productId : zcz004
                 * equipmentId : zcz004100411
                 * executeTime : 00:02
                 * executeTimePoint : 17:43:50
                 * switchStatus : 0
                 * createTime : 1658223675812
                 */

                private int id;
                private String brandId;
                private String modeId;
                private String productId;
                private String equipmentId;
                private String executeTime;
                private String executeTimePoint;
                private int switchStatus;
                private String createTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getBrandId() {
                    return brandId;
                }

                public void setBrandId(String brandId) {
                    this.brandId = brandId;
                }

                public String getModeId() {
                    return modeId;
                }

                public void setModeId(String modeId) {
                    this.modeId = modeId;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getExecuteTime() {
                    return executeTime;
                }

                public void setExecuteTime(String executeTime) {
                    this.executeTime = executeTime;
                }

                public String getExecuteTimePoint() {
                    return executeTimePoint;
                }

                public void setExecuteTimePoint(String executeTimePoint) {
                    this.executeTimePoint = executeTimePoint;
                }

                public int getSwitchStatus() {
                    return switchStatus;
                }

                public void setSwitchStatus(int switchStatus) {
                    this.switchStatus = switchStatus;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }
            }

            public static class TimingsBean implements Serializable {
                /**
                 * id : 12
                 * brandId : 3
                 * modeId : 452f2959e61658142200308
                 * productId : zcz004
                 * equipmentId : zcz004100411
                 * week : 0
                 * startTime : 17:34
                 * endTime : 17:33
                 * openIf : 2
                 * closeIf : 2
                 * createTime : 1658223073867
                 */

                private int id;
                private String brandId;
                private String modeId;
                private String productId;
                private String equipmentId;
                private String week;
                private String startTime;
                private String endTime;
                private int openIf;
                private int closeIf;
                private String createTime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getBrandId() {
                    return brandId;
                }

                public void setBrandId(String brandId) {
                    this.brandId = brandId;
                }

                public String getModeId() {
                    return modeId;
                }

                public void setModeId(String modeId) {
                    this.modeId = modeId;
                }

                public String getProductId() {
                    return productId;
                }

                public void setProductId(String productId) {
                    this.productId = productId;
                }

                public String getEquipmentId() {
                    return equipmentId;
                }

                public void setEquipmentId(String equipmentId) {
                    this.equipmentId = equipmentId;
                }

                public String getWeek() {
                    return week;
                }

                public void setWeek(String week) {
                    this.week = week;
                }

                public String getStartTime() {
                    return startTime;
                }

                public void setStartTime(String startTime) {
                    this.startTime = startTime;
                }

                public String getEndTime() {
                    return endTime;
                }

                public void setEndTime(String endTime) {
                    this.endTime = endTime;
                }

                public int getOpenIf() {
                    return openIf;
                }

                public void setOpenIf(int openIf) {
                    this.openIf = openIf;
                }

                public int getCloseIf() {
                    return closeIf;
                }

                public void setCloseIf(int closeIf) {
                    this.closeIf = closeIf;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }
            }

            public static class EntityBean implements Serializable {
                /**
                 * equipmentNote : 红外遥控
                 * device_id : 1
                 * kfid : 10122
                 * userId : minApp108881
                 * productId : zcz004
                 * infraredBinId : 25
                 * equipmentId : zcz004100411
                 * mac : df3c66db8eded811
                 * brand_id : 3
                 * nick : 美的空调
                 * equipmentState : 2
                 * rcRoom : 客厅
                 * rcOperateCode : 01,00,00,01,02,21,00,00
                 * modeId : 452f2959e61658142200308
                 * modeHead : 025F,1F
                 * rcCreateTime : 1658223016440
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

                public void setDeviceId(String device_id) {
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

                public void setBrandId(String brand_id) {
                    this.brand_id = brand_id;
                }

                public String getNick() {
                    return nick;
                }

                /**
                 map2.put("1", new RcDeviceBean(1, "空调", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("2", new RcDeviceBean(2, "电视", R.mipmap.rc_tving, R.mipmap.rc_tved));
                 map2.put("3", new RcDeviceBean(3, "机顶盒", R.mipmap.rc_top_boxing, R.mipmap.rc_top_boxed));
                 map2.put("4", new RcDeviceBean(4, "DVD", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("5", new RcDeviceBean(5, "电风扇", R.mipmap.rc_faning, R.mipmap.rc_faned));
                 map2.put("6", new RcDeviceBean(6, "空气净化器", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("7", new RcDeviceBean(7, "iptv", R.mipmap.rc_iptving, R.mipmap.rc_iptved));
                 map2.put("8", new RcDeviceBean(8, "投影仪", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("9", new RcDeviceBean(9, "功放", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("10", new RcDeviceBean(10, "热水器", R.mipmap.rc_watering, R.mipmap.rc_watered));
                 map2.put("11", new RcDeviceBean(11, "灯泡", R.mipmap.rc_lighting, R.mipmap.rc_lighted));
                 map2.put("12", new RcDeviceBean(12, "插座", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("13", new RcDeviceBean(13, "扫地机", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("14", new RcDeviceBean(14, "加湿器", R.mipmap.rc_cold_airing, R.mipmap.rc_cold_aired));
                 map2.put("15", new RcDeviceBean(15, "台灯", R.mipmap.rc_lighting, R.mipmap.rc_lighted));
                 */
                public int getIconing(String deviceId) {
                    int resId = 0;
                    if (TextUtils.equals(deviceId, "2")) {
                        resId = R.mipmap.rc_tving;
                    } else if (TextUtils.equals(deviceId, "3")) {
                        resId = R.mipmap.rc_top_boxing;
                    } else if (TextUtils.equals(deviceId, "5")) {
                        resId = R.mipmap.rc_faning;
                    } else if (TextUtils.equals(deviceId, "7")) {
                        resId = R.mipmap.rc_iptving;
                    } else if (TextUtils.equals(deviceId, "10")) {
                        resId = R.mipmap.rc_watering;
                    } else if (TextUtils.equals(deviceId, "11")) {
                        resId = R.mipmap.rc_lighting;
                    } else if (TextUtils.equals(deviceId, "15")) {
                        resId = R.mipmap.rc_lighting;
                    }else{
                        resId = R.mipmap.rc_cold_airing;
                    }
                    return resId;
                }

                public int getIconed(String deviceId) {
                    int resId = 0;
                    if (TextUtils.equals(deviceId, "2")) {
                        resId = R.mipmap.rc_tved;
                    } else if (TextUtils.equals(deviceId, "3")) {
                        resId = R.mipmap.rc_top_boxed;
                    } else if (TextUtils.equals(deviceId, "5")) {
                        resId = R.mipmap.rc_faned;
                    } else if (TextUtils.equals(deviceId, "7")) {
                        resId = R.mipmap.rc_iptved;
                    } else if (TextUtils.equals(deviceId, "10")) {
                        resId = R.mipmap.rc_watered;
                    } else if (TextUtils.equals(deviceId, "11")) {
                        resId = R.mipmap.rc_lighted;
                    } else if (TextUtils.equals(deviceId, "15")) {
                        resId = R.mipmap.rc_lighted;
                    }else{
                        resId = R.mipmap.rc_cold_aired;
                    }
                    return resId;
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
}
