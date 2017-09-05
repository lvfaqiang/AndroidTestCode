package com.lvfq.code;

/**
 * ModelBean
 *
 * @author lvfq
 * @Github: https://github.com/lvfaqiang
 * @Blog: http://blog.csdn.net/lv_fq
 * @date 2017/8/4 下午10:30
 * @desc :
 */

public class ModelBean {


    /**
     * errorCtx : null
     * resultData : {"isMainServer ":true,"serverNumber":1234}
     * success : true
     */

    private ResultDataBean resultData;
    private boolean success;

    public ResultDataBean getResultData() {
        return resultData;
    }

    public void setResultData(ResultDataBean resultData) {
        this.resultData = resultData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultDataBean {
        /**
         * isMainServer  : true
         * serverNumber : 1234
         */

        private boolean isMainServer;
        private int serverNumber;

        public boolean isMainServer() {
            return isMainServer;
        }

        public void setMainServer(boolean mainServer) {
            isMainServer = mainServer;
        }

        public int getServerNumber() {
            return serverNumber;
        }

        public void setServerNumber(int serverNumber) {
            this.serverNumber = serverNumber;
        }
    }
}
