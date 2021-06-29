
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.security.KeyFactory

import java.security.PrivateKey
import java.security.PublicKey
import com.example.kotlinmvp.utils.Base64

import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher

class RSAUtils {

    val transformation = "RSA"
    //注意自己生成的密钥对的bits长度
    val ENCRYPT_MAX_SIZE = 245//加密每次最大加密字节
    val DECRYPT_MAX_SIZE = 256//解密每次最大加密字节
    var publicKey: PublicKey? = null
    var privateKey: PrivateKey? = null
    val publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjGydu+oKSDLzEjd43Xq5tCx34HR2+2eMk9SJUy2T08Iznel8lkW+gRUHdymvguM11vBNk7rxHS7fe321SSSFOF4oJYK8XdiJ3NKOira3m9RSmI0vtL93zd+QlAcg2qoKI++hP3HdLA4F4eATYGjCEgeZhpwbS5A8elNb5LJkVz2Oex7SdewWAp1awaS2eFOOBizwhRWUPAyBQXBRR3ZG+3VYAf7xyVEXPvAbVpGrRTGYCcbe7+t2EpUO7yO//+Yq4MhxqsyZCZ6Dh2/A32u7UKKSKwHZMfeUfSIb/Eo5thhg5R9x7/mQ1au+bTZZAioIwNVl3f9d88SF2LXl+hQ5KQIDAQAB"

    fun keyPairGenerator(){
        //字符串转成密钥对对象
        val keyFactory = KeyFactory.getInstance("RSA")
//        val privateKey = keyFactory.generatePrivate(PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyString)))

        publicKey = keyFactory.generatePublic(X509EncodedKeySpec(  Base64.decode(publicKeyString,0)))

    }

    /**
     * 私钥加密
     */
    fun encryptByPrivateKey(str: String, privateKey: PrivateKey): String {
        val byteArray = str.toByteArray()
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, privateKey)

        //定义缓冲区
        var temp: ByteArray? = null
        //当前偏移量
        var offset = 0

        val outputStream = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            //剩余的部分大于最大加密字段，则加密117个字节的最大长度
            if (byteArray.size - offset >= ENCRYPT_MAX_SIZE) {
                temp = cipher.doFinal(byteArray, offset, ENCRYPT_MAX_SIZE)
                //偏移量增加117
                offset += ENCRYPT_MAX_SIZE
            } else {
                //如果剩余的字节数小于117，则加密剩余的全部
                temp = cipher.doFinal(byteArray, offset, (byteArray.size - offset))
                offset = byteArray.size
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return Base64.encodeToString(outputStream.toByteArray(),0)
//        return  Base64.getEncoder().encodeToString()
    }

    /**
     * 公钥加密
     */
    fun encryptByPublicKey(str: String): String {
        val byteArray = str.toByteArray()
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        var temp: ByteArray? = null
        var offset = 0

        val outputStream = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            if (byteArray.size - offset >= ENCRYPT_MAX_SIZE) {
                temp = cipher.doFinal(byteArray, offset, ENCRYPT_MAX_SIZE)
                offset += ENCRYPT_MAX_SIZE
            } else {
                temp = cipher.doFinal(byteArray, offset, (byteArray.size - offset))
                offset = byteArray.size
            }
            outputStream.write(temp)
        }

        outputStream.close()
        return Base64.encodeToString(outputStream.toByteArray(),0)
//        return  Base64.getEncoder().encodeToString(outputStream.toByteArray())
    }

    /**
     * 私钥解密
     * 注意Exception in thread "main" javax.crypto.IllegalBlockSizeException:
     * Data must not be longer than 256 bytes
     * 关于到底是128个字节还是256个，我也很迷糊了，我写成128的时候就报这个错误，改成256后就没事了
     */
    fun decryptByPrivateKey(str: String, privateKey: PrivateKey): String {
//        val byteArray = Base64.getDecoder().decode(str)
        val byteArray = Base64.decode(str,0)
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        //定义缓冲区
        var temp: ByteArray? = null
        //当前偏移量
        var offset = 0

        val outputStream = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            //剩余的部分大于最大解密字段，则加密限制的最大长度
            if (byteArray.size - offset >= DECRYPT_MAX_SIZE) {
                temp = cipher.doFinal(byteArray, offset, DECRYPT_MAX_SIZE)
                //偏移量增加128
                offset += DECRYPT_MAX_SIZE
            } else {
                //如果剩余的字节数小于最大长度，则解密剩余的全部
                temp = cipher.doFinal(byteArray, offset, (byteArray.size - offset))
                offset = byteArray.size
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return String(outputStream.toByteArray())
    }

    /**
     * 公钥解密
     */
    fun decryptByPublicKey(str: String): String {
        val byteArray = Base64.decode(str,0)
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, publicKey)

        var temp: ByteArray? = null
        var offset = 0

        val outputStream = ByteArrayOutputStream()

        while (byteArray.size - offset > 0) {
            if (byteArray.size - offset >= DECRYPT_MAX_SIZE) {
                temp = cipher.doFinal(byteArray, offset, DECRYPT_MAX_SIZE)
                offset += DECRYPT_MAX_SIZE
            } else {
                temp = cipher.doFinal(byteArray, offset, (byteArray.size - offset))
                offset = byteArray.size
            }
            outputStream.write(temp)
        }
        outputStream.close()
        return String(outputStream.toByteArray(),Charset.forName("UTF-8"))
    }


    /**
     * 公钥解密2
     * @param input 密文
     * @param privateKey 公钥
     */
    fun decryptByPublicKey2(input: String): String {
        //创建cipher对象
        var cipher = Cipher.getInstance(transformation)
        //初始化cipher
        cipher.init(Cipher.DECRYPT_MODE, publicKey)
        //加密/解密
        val encrypt = cipher.doFinal(Base64.decode(input,0))
        return String(encrypt)
    }

}