
//检查更新
         createBuilder().check();
         //更新配置
    private UpdateBuilder createBuilder() {
        UpdateBuilder builder = UpdateBuilder.create(createNewConfig());
=
        builder.setDownloadCallback(callback);
        builder.setCheckCallback(new CheckCallback() {
            @Override
            public void onCheckStart() {

            }

            @Override
            public void hasUpdate(Update update) {

            }

            @Override
            public void noUpdate() {

            }

            @Override
            public void onCheckError(Throwable t) {

            }

            @Override
            public void onUserCancel() {

            }

            @Override
            public void onCheckIgnore(Update update) {

            }
        });
//        if (!newConfig.isDefaultSelected()) {
//            builder = UpdateBuilder.create(createNewConfig());
//        }

        // 根据各项是否选择使用默认配置进行使用更新。
        //builder.setCheckWorker(OkhttpCheckWorker.class);
        //  builder.setCheckNotifier(new NotificationUpdateCreator());
        //builder.setInstallNotifier(new NotificationInstallCreator());

       // if ( isPermissionGrant) {
           // builder.setFileCreator(new CustomApkFileCreator());
      //  }

       // builder.setUpdateStrategy(new AllDialogShowStrategy());

      //  builder.setDownloadNotifier(new NotificationDownloadCreator());
        builder.setDownloadWorker(OkhttpDownloadWorker.class);
        return builder;
    }
    private UpdateConfig createNewConfig() {
        return UpdateConfig.createConfig()
                .setUrl(HttpConfig.UPDATE_URL)
                .setUpdateParser(new UpdateParser() {
                    @Override
                    public Update parse(String httpResponse) throws Exception {
                        org.json.JSONObject object = new org.json.JSONObject(httpResponse);
                        Update update = new Update();
                        // 此apk包的下载地址 f65d22c8add672796bdaa5140c5af892
                        update.setUpdateUrl(object.optString("update_url"));

                        // 此apk包的版本号
                        update.setVersionCode(object.optInt("update_ver_code"));
                        // 此apk包的版本名称
                        update.setVersionName(object.optString("update_ver_name"));
                        // 此apk包的更新内容
                        update.setUpdateContent(object.optString("update_content").replace("#","\n"));
                        // 此apk包是否为强制更新
                        update.setForced(object.optBoolean("is_must_update",false)); 
                        // 是否显示忽略此次版本更新按钮
                        update.setIgnore(object.optBoolean("ignore_able",false));
                        update.setMd5(object.optString("md5"));
                         Log.e(TAG,"md5="+update.getMd5());
                        return update;
                    }
                });
    }