package com.hyssop.framework.util.http.aes;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES Coder<br/>
 * secret key length: 128bit, default: 128 bit<br/>
 * mode: ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/>
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/
 * 
 * @author Aub
 * 
 */
public class AESCoder {

	private static final Logger LOG = LoggerFactory
			.getLogger(AESCoder.class);

	/**
	 * 密钥算法
	 */
	private static final String KEY_ALGORITHM = "AES";

	private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

	/**
	 * 初始化密钥
	 * 
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static byte[] initSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (Exception e) {
			LOG.error("", e);
			return new byte[0];
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		byte[] keyData = secretKey.getEncoded();

		return keyData;
	}

	/**
	 * 初始化密钥
	 *
	 * @return byte[] 密钥
	 * @throws Exception
	 */
	public static SecretKey getSecretKey() {
		// 返回生成指定算法的秘密密钥的 KeyGenerator 对象
		KeyGenerator kg = null;
		try {
			kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		} catch (Exception e) {
			LOG.error("", e);
			return null;
		}
		// 初始化此密钥生成器，使其具有确定的密钥大小
		// AES 要求密钥长度为 128
		kg.init(128);
		// 生成一个密钥
		SecretKey secretKey = kg.generateKey();
		return secretKey;
	}
	/**
	 * 转换密钥
	 * 
	 * @param key
	 *            二进制密钥
	 * @return 密钥
	 */
	public static Key toKey(byte[] key) {
		// 生成密钥
		return new SecretKeySpec(key, KEY_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            二进制密钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            二进制密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm)
			throws Exception {
		// 还原密钥
		Key k = toKey(key);
		return encrypt(data, k, cipherAlgorithm);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @param cipherAlgorithm
	 *            加密算法/工作模式/填充方式
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm)
			throws Exception {
		// long start = System.currentTimeMillis();
		// 实例化
		Cipher cipher = Cipher.getInstance(cipherAlgorithm);
		// 使用密钥初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, key);
		// 执行操作
		byte[] _data = cipher.doFinal(data);
		/*
		 * if(LOG.isDebugEnabled()) {
		 * LOG.debug(String.format("AES encrypt consume %s ms",
		 * System.currentTimeMillis() - start)); }
		 */
		return _data;
	}

	/**
	 * 初始文本生成加密串
	 * 
	 * @param val
	 * @return
	 * @author asflex
	 * @date 2013-10-16下午2:38:43　
	 * @modify 2013-10-16下午2:38:43
	 */
	public static String encrypt(String val) {
		byte[] keyData = initSecretKey();
		Key key = toKey(keyData);
		try {
			byte[] encryptData = encrypt(val.getBytes(), key);
			return Hex.encodeHexString(keyData)
					+ Hex.encodeHexString(encryptData);
		} catch (Exception e) {
			LOG.error("", e);
		}

		return null;
	}

    public static String hexEnc(String data, String skey) {
        try {
            Key key = toKey(Hex.decodeHex(skey.toCharArray()));
            return Hex.encodeHexString(encrypt(data.getBytes(), key));
        } catch (Exception e) {
            return "";
        }
    }

	public static void main(String[] args) throws Exception {

		String s = encrypt("{\"orderNo\":\"bj1140123163218981da00f\",\"mobile\":18688745114,\"domain\":\"bj1.trade.qunar.com\",\"otaType\":2}");
		System.out.println(s);
		s = encrypt("{\"orderNo\":\"bj1140123163218981da00f\",\"mobile\":18688745114,\"domain\":\"bj1.trade.qunar.com\",\"otaType\":2}");
		System.out.println(s);

		// System.out.println(decrypt(s));
	}
}
