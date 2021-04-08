package com.example.kotlinmvp.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;

public class UriUtils {
    private static final String SCHEME_FILE = "file";
    private static final String SCHEME_CONTENT = "content";

    public UriUtils() {
    }

    public static Uri parseUri(String uri) {
        try {
            return Uri.parse(Uri.decode(uri));
        } catch (Exception var2) {
            return Uri.EMPTY;
        }
    }

    public static String getHost(String uri) {
        try {
            return parseUri(uri).getHost();
        } catch (Exception var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static Uri newWithResourceId(Context context, int resId) {
        return UriUtils.parseUri("android.resource://" + context.getPackageName() + "/" + resId);
    }

    public static Uri newWithResourceId(int resId) {
        return (new Builder()).scheme("res").authority("").path(String.valueOf(resId)).build();
    }

    public static Uri newWithFilePath(String filePath) {
        return (new Builder()).scheme("file").authority("").path(filePath).build();
    }

    @SuppressLint({"NewApi"})
    public static String getFromMediaUri(Context context, Uri uri) {
        if (uri == null) {
            return null;
        } else {
            String path = null;
            if ("file".equals(uri.getScheme())) {
                path = uri.getPath();
            } else if ("content".equals(uri.getScheme())) {
                String[] filePathColumn;
                Cursor cursor;
                int columnIndex;
                String sel;
                if (VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {
                    String wholeID = DocumentsContract.getDocumentId(uri);
                    String id = wholeID.split(":")[1];
                    String[] column = new String[]{"_data"};
                    sel = "_id=?";
                    cursor = context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{id}, (String) null);
                    columnIndex = cursor.getColumnIndex(column[0]);
                    if (cursor.moveToFirst()) {
                        path = cursor.getString(columnIndex);
                    }

                    cursor.close();
                } else {
                    filePathColumn = new String[]{"_data"};
                    cursor = context.getContentResolver().query(uri, filePathColumn, (String) null, (String[]) null, (String) null);
                    columnIndex = cursor.getColumnIndexOrThrow("_data");
                    if (cursor.moveToFirst()) {
                        path = cursor.getString(columnIndex);
                    }

                    cursor.close();
                }

                if (!TextUtils.isEmpty(path)) {
                    return path;
                }

                filePathColumn = new String[]{"_data", "_display_name"};
                cursor = null;

                try {
                    cursor = context.getContentResolver().query(uri, filePathColumn, (String) null, (String[]) null, (String) null);
                    if (cursor == null || !cursor.moveToFirst()) {
                        return path;
                    }

                    columnIndex = uri.toString().startsWith("content://com.google.android.gallery3d") ? cursor.getColumnIndex("_display_name") : cursor.getColumnIndex("_data");
                    if (columnIndex == -1) {
                        return path;
                    }

                    path = cursor.getString(columnIndex);
                    if (TextUtils.isEmpty(path)) {
                        return path;
                    }

                    sel = path;
                } catch (SecurityException var12) {
                    return path;
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }

                }

                return sel;
            }

            return path;
        }
    }
}
