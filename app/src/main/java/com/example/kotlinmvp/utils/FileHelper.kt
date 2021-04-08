package com.example.kotlinmvp.utils

import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import java.io.*
import java.nio.channels.FileChannel


/**
 * Created by linzehao
 * time: 2018/1/4.
 * info:
 */
object FileHelper {

    fun getFileType(path: String): String = path.substring(path.lastIndexOf(".") + 1)

    fun getFileTypeByFile(file: File) {
        getFileType(file.absoluteFile.toString())
    }

    fun isExists(file: File?): Boolean {
        return file != null && file.exists()
    }

    fun isGifFile(path: String): Boolean {
        var inputStream: InputStream? = null
        return try {
            var buf = ByteArray(1)
            inputStream = FileInputStream(path)
            inputStream.read(buf)
            buf[0] == 71.toByte()
        } catch (e: Exception) {
            e.printStackTrace()
            false
        } finally {

        }
    }

    fun copyFile(path: String, newPath: String) {
        var inputChannel: FileChannel? = null
        var outputChannel: FileChannel? = null
        try {
            inputChannel = FileInputStream(path).channel
            outputChannel = FileOutputStream(newPath).channel
            outputChannel?.transferFrom(inputChannel, 0, inputChannel?.size() ?: 0)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputChannel?.close()
            outputChannel?.close()
        }
    }

    fun file2Byte(tradeFile: File): ByteArray? {
        var buffer: ByteArray? = null
        try {
            val fis = FileInputStream(tradeFile)
            val bos = ByteArrayOutputStream()
            val b = ByteArray(1024)
            var n = 0

            do {
                n = fis.read(b)
                if (n == -1) {
                    break
                }
                bos.write(b, 0, n)

            } while (true)

            fis.close()
            bos.close()
            buffer = bos.toByteArray()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return buffer
    }

    fun byte2File(buf: ByteArray, file: File) {
        var bos: BufferedOutputStream? = null
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bos = BufferedOutputStream(fos)
            bos.write(buf)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bos != null) {
                try {
                    bos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun insertImageToGallery(context: Context, imageFile: File) :Boolean{
        try {
            // 把文件插入到系统图库
            val imageUri = MediaStore.Images.Media.insertImage(context.contentResolver, imageFile.absolutePath, imageFile.name, null)
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis() / 1000)
            values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000)
            values.put(MediaStore.Images.Media.SIZE, imageFile.length())
            context.contentResolver.update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values, MediaStore.Images.Media.DATA + " = ?", arrayOf<String>(
                UriUtils.getFromMediaUri(context, UriUtils.parseUri(imageUri))))
            return true

        } catch (t: Throwable) {
            t.printStackTrace()
            return false
        }
        return false
    }

    fun getVideoContentValues(paramFile: File, paramLong: Long): ContentValues {
        val localContentValues = ContentValues()
        localContentValues.put("title", paramFile.name)
        localContentValues.put("_display_name", paramFile.name)
        localContentValues.put("mime_type", "video/mp4")
        localContentValues.put("datetaken", paramLong)
        localContentValues.put("date_modified", paramLong)
        localContentValues.put("date_added", paramLong)
        localContentValues.put("_data", paramFile.absolutePath)
        localContentValues.put("_size", paramFile.length())
        return localContentValues
    }



    fun writeFileData( file: File,  conent:String) {
        var out :BufferedWriter? =null
        try {
            out =  BufferedWriter( OutputStreamWriter( FileOutputStream(file, true)))
            out.write(conent);
        } catch ( e:Exception) {
            e.printStackTrace();
        } finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch ( e:IOException) {
                e.printStackTrace();
            }
        }
    }
}