package com.wuyonghua.learn;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.SignatureDSA;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;

/**
 * @Description TODO  测试shiro提供的加密api
 *                    默认使用SimpleCredentialsMatcher
 **/
public class Encrypt {
    public static void main(String[] args) {
        String myPassword = "wyh989795";
        //使用shiro提供的md5进行加密
        Md5Hash pwdAfterEn = new Md5Hash(myPassword);
        System.out.println(pwdAfterEn);//05d896c7f8f046451158abde84ac6247
        //使用shiro提供的加盐md5进行加密
        Md5Hash pwdAfterEn1 = new Md5Hash(myPassword, "salt");
        System.out.println(pwdAfterEn1);//045aa45aded8907a35240e27d5ea442
        //使用多次迭代加盐md5进行加密
        Md5Hash pwdAfterEn2 = new Md5Hash(myPassword, "salt", 3);
        System.out.println(pwdAfterEn2);//42627c91d64b21edd62994234743002f
        //SHA-512哈希
        String password1 = new Sha512Hash(myPassword, "MD5", 3).toBase64();
        System.out.println(password1);//rz5VCSDI7UPzcPbYdRGaDv3xeLgWtdAhh2b97RR1l2w4Wiz7oAmR54c/e8YXxq3AAg8rHxesQrGVs2CxsG7EJQ==

    }
}
