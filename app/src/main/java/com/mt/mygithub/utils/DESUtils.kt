package com.mt.mygithub.utils

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.SecureRandom
import java.util.logging.Logger
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESKeySpec
import kotlin.experimental.and

/**
 * @description:
 * @author: jasonhe .
 * @data:   On 2022/8/17
 */
class DESUtils {
    companion object {
        final const val SECURITY_KEY = "!(OA^8B#6"

        /**
         * DES加密
         *
         * @param content 字符串内容
         */
        fun enCrypt(content: String?): String? {
            content?.let {
                return des(content!!, SECURITY_KEY, Cipher.ENCRYPT_MODE, false)
            }
            return ""
        }

        /**
         * DES解密
         *
         * @param content 字符串内容
         */
        fun deCrypt(content: String?): String? {
            content?.let {
                return des(content!!, SECURITY_KEY, Cipher.DECRYPT_MODE, false)
            }
            return ""
        }


        /**
         * DES加密/解密公共方法
         *
         * @param content  字符串内容
         * @param password 密钥
         * @param type     加密：[Cipher.ENCRYPT_MODE]，解密：[Cipher.DECRYPT_MODE]
         */
        private fun des(content: String, password: String, type: Int, base64: Boolean): String? {
            try {
                val random = SecureRandom()
                val desKey = DESKeySpec(password.toByteArray())
                val keyFactory = SecretKeyFactory.getInstance("DES")
                val cipher = Cipher.getInstance("DES")
                cipher.init(type, keyFactory.generateSecret(desKey), random)
                return if (type == Cipher.ENCRYPT_MODE) {
                    val byteContent = content.toByteArray(StandardCharsets.UTF_8)
                    if (base64) {
                        Base64.encodeToString(cipher.doFinal(byteContent), Base64.DEFAULT)
                    } else {
                        parseByte2HexStr(cipher.doFinal(byteContent))
                    }
                } else {
                    val byteContent: ByteArray?
                    byteContent = if (base64) {
                        Base64.decode(content, Base64.DEFAULT)
                    } else {
                        parseHexStr2Byte(content)
                    }
                    assert(byteContent != null)
                    String(cipher.doFinal(byteContent))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

        /**
         * 将二进制转换成16进制
         *
         * @param buf
         * @return
         */
        private fun parseByte2HexStr(buf: ByteArray): String? {
            val sb = StringBuffer()
            for (i in buf.indices) {
                var hex = Integer.toHexString((buf[i] and 0xFF.toByte()).toInt())
                if (hex.length == 1) {
                    hex = "0$hex"
                }
                sb.append(hex.toUpperCase())
            }
            return sb.toString()
        }

        /**
         * 将16进制转换为二进制
         *
         * @param hexStr
         * @return
         */
        private fun parseHexStr2Byte(hexStr: String): ByteArray? {
            if (hexStr.length < 1) return null
            val result = ByteArray(hexStr.length / 2)
            for (i in 0 until hexStr.length / 2) {
                val high = hexStr.substring(i * 2, i * 2 + 1).toInt(16)
                val low = hexStr.substring(i * 2 + 1, i * 2 + 2).toInt(
                    16
                )
                result[i] = (high * 16 + low).toByte()
            }
            return result
        }
    }


}