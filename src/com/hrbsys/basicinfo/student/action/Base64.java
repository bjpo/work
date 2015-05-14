package com.hrbsys.basicinfo.student.action;

import java.util.Arrays;
import java.io.UnsupportedEncodingException;
public class Base64 {
 
    private static final char[] CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final int[] INV = new int[256];
 
    static {
        Arrays.fill(INV, -1);
        for (int i = 0, iS = CHARS.length; i < iS; i++) {
            INV[CHARS[i]] = i;
        }
        INV['='] = 0;
    }
    public static char[] getAlphabet() {
        return CHARS.clone();
    }
    public static char[] encodeToChar(byte[] arr, boolean lineSeparator) {
        int len = arr != null ? arr.length : 0;
        if (len == 0) {
            return new char[0];
        }
        int evenlen = (len / 3) * 3;
        int cnt = ((len - 1) / 3 + 1) << 2;
        int destLen = cnt + (lineSeparator ? (cnt - 1) / 76 << 1 : 0);
        char[] dest = new char[destLen];
        for (int s = 0, d = 0, cc = 0; s < evenlen;) {
            int i = (arr[s++] & 0xff) << 16 | (arr[s++] & 0xff) << 8
                    | (arr[s++] & 0xff);
            dest[d++] = CHARS[(i >>> 18) & 0x3f];
            dest[d++] = CHARS[(i >>> 12) & 0x3f];
            dest[d++] = CHARS[(i >>> 6) & 0x3f];
            dest[d++] = CHARS[i & 0x3f];
            if (lineSeparator && (++cc == 19) && (d < (destLen - 2))) {
                dest[d++] = '\r';
                dest[d++] = '\n';
                cc = 0;
            }
        }
        int left = len - evenlen; // 0 - 2.
        if (left > 0) {
            int i = ((arr[evenlen] & 0xff) << 10)
                    | (left == 2 ? ((arr[len - 1] & 0xff) << 2) : 0);
            dest[destLen - 4] = CHARS[i >> 12];
            dest[destLen - 3] = CHARS[(i >>> 6) & 0x3f];
            dest[destLen - 2] = left == 2 ? CHARS[i & 0x3f] : '=';
            dest[destLen - 1] = '=';
        }
        return dest;
    }
    public static String encodeToString(byte[] arr) {
        return new String(encodeToChar(arr, false));
    }
 
    public static byte[] decode(String s) {
        int length = s.length();
        if (length == 0) {
            return new byte[0];
        }
 
        int sndx = 0, endx = length - 1;
        int pad = s.charAt(endx) == '=' ? (s.charAt(endx - 1) == '=' ? 2 : 1)
                : 0;
        int cnt = endx - sndx + 1;
        int sepCnt = length > 76 ? (s.charAt(76) == '\r' ? cnt / 78 : 0) << 1
                : 0;
        int len = ((cnt - sepCnt) * 6 >> 3) - pad;
        byte[] dest = new byte[len];
 
        int d = 0;
        for (int cc = 0, eLen = (len / 3) * 3; d < eLen;) {
            int i = INV[s.charAt(sndx++)] << 18 | INV[s.charAt(sndx++)] << 12
                    | INV[s.charAt(sndx++)] << 6 | INV[s.charAt(sndx++)];
 
            dest[d++] = (byte) (i >> 16);
            dest[d++] = (byte) (i >> 8);
            dest[d++] = (byte) i;
 
            if (sepCnt > 0 && ++cc == 19) {
                sndx += 2;
                cc = 0;
            }
        }
 
        if (d < len) {
            int i = 0;
            for (int j = 0; sndx <= endx - pad; j++) {
                i |= INV[s.charAt(sndx++)] << (18 - j * 6);
            }
            for (int r = 16; d < len; r -= 8) {
                dest[d++] = (byte) (i >> r);
            }
        }
 
        return dest;
    }
 
}
