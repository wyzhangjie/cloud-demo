package com.hyssop.framework.util.http.aes;

import com.google.common.base.Charsets;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PoolUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description:    加密类
 * @Author:         zhjie.zhang
 * @CreateDate:     2019/7/9$ 18:37$
 * @UpdateUser:     zhjie.zhang
 * @UpdateDate:     2019/7/9$ 18:37$
 * @Version:        1.0
 */
@Slf4j
@Component
public class AES {

    private final ObjectPool<Cipher> encryptPool;
    private final ObjectPool<Cipher> decryptPool;

   public AES(){
       this(AESCoder.getSecretKey(),10);
   }

    private AES(SecretKey key, int maxPoolSize) {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(maxPoolSize == 0 ? 1 : maxPoolSize);
        poolConfig.setMaxIdle(5);

        encryptPool = PoolUtils.synchronizedPool(new GenericObjectPool<>(new Factory(Cipher.ENCRYPT_MODE, key), poolConfig));

        decryptPool = PoolUtils.synchronizedPool(new GenericObjectPool<>(new Factory(Cipher.DECRYPT_MODE, key), poolConfig));
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @return 加密数据
     */
    public byte[] encrypt(String data) {
        try {
            Cipher cipher = encryptPool.borrowObject();
            byte[] bytes = cipher.doFinal(data.getBytes(Charsets.UTF_8));

            encryptPool.returnObject(cipher);
            return bytes;
        } catch (Exception e) {
            log.error("", e);
        }

        return new byte[0];
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @return 加密数据
     */
    public byte[] encryptByte(byte[] data) {
        try {
            Cipher cipher = encryptPool.borrowObject();
            byte[] bytes = cipher.doFinal(data);

            encryptPool.returnObject(cipher);
            return bytes;
        } catch (Exception e) {
            log.error("", e);
        }

        return new byte[0];
    }

    /**
     *
     * @param data 已加密数据
     * @return 解密数据 utf-8 编码
     */
    public Optional<String> decrypt(byte[] data) {
        try {
            Cipher cipher = decryptPool.borrowObject();
            byte[] bytes = cipher.doFinal(data);

            Optional<String> encrypt = Optional.of(new String(bytes, Charsets.UTF_8));

            decryptPool.returnObject(cipher);
            return encrypt;
        } catch (Exception e) {
            log.error("", e);
        }

        return Optional.empty();
    }


    /**
     *
     * @param data 已加密数据
     * @return 解密数据 utf-8 编码
     */
    public byte[] decryptByte(byte[] data) {
        try {
            Cipher cipher = decryptPool.borrowObject();
            byte[] bytes = cipher.doFinal(data);

            decryptPool.returnObject(cipher);
            return bytes;
        } catch (Exception e) {
            log.error("", e);
        }

        return new byte[0];
    }

    public static Optional<AES> create(String key, int maxPoolSize) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes());

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, secureRandom);

            return Optional.of(new AES(keyGenerator.generateKey(), maxPoolSize));
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        }

        return Optional.empty();
    }

    private static class Factory implements PooledObjectFactory<Cipher> {
        private final int mode;

        private final SecretKey key;

        private Factory(int mode, SecretKey key) {
            this.mode = mode;
            this.key = key;
        }

        @Override
        public PooledObject<Cipher> makeObject() throws Exception {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(this.mode, this.key);

            return new DefaultPooledObject<>(cipher);
        }

        @Override
        public void destroyObject(PooledObject<Cipher> pooledObject) throws Exception {
        }

        @Override
        public boolean validateObject(PooledObject<Cipher> pooledObject) {
            return true;
        }

        @Override
        public void activateObject(PooledObject<Cipher> pooledObject) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<Cipher> pooledObject) throws Exception {

        }
    }

}
