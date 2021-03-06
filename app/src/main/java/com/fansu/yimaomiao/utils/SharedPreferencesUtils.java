package com.fansu.yimaomiao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.fansu.yimaomiao.Constans;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;


/**
 * Created by leo on 16/11/1.
 */

public class SharedPreferencesUtils {
    private static SharedPreferences sp;
    private static String SP_NAME = Constans.SHAREPREFERENCE_NAME;


    /**
     * 保存布尔值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        saveBoolean(context, SP_NAME, key, value);
    }

    public static void saveBoolean(Context context, String sp_name, String key, boolean value) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        saveString(context, SP_NAME, key, value);

    }

    public static void saveString(Context context, String sp_name, String key, String value) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putString(key, value).commit();

    }

    /**
     * 保存long型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        saveLong(context, SP_NAME, key, value);
    }

    public static void saveLong(Context context, String sp_name, String key, long value) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 保存int型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        saveInt(context, SP_NAME, key, value);
    }

    public static void saveInt(Context context, String sp_name, String key, int value) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 保存float型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveFloat(Context context, String key, float value) {
        saveFloat(context, SP_NAME, key, value);
    }

    public static void saveFloat(Context context, String sp_name, String key, float value) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 获取字符值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        sp = context.getSharedPreferences(SP_NAME, 0);
        return getString(context, SP_NAME, key, defValue);
    }

    public static String getString(Context context, String sp_name, String key, String defValue) {
        sp = context.getSharedPreferences(sp_name, 0);
        return sp.getString(key, defValue);
    }

    /**
     * 获取int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        sp = context.getSharedPreferences(SP_NAME, 0);
        return getInt(context, SP_NAME, key, defValue);
    }

    public static int getInt(Context context, String sp_name, String key, int defValue) {
        sp = context.getSharedPreferences(sp_name, 0);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取long值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        return getLong(context, SP_NAME, key, defValue);
    }

    public static long getLong(Context context, String sp_name, String key, long defValue) {
        sp = context.getSharedPreferences(sp_name, 0);
        return sp.getLong(key, defValue);
    }

    /**
     * 获取float值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        return getFloat(context, SP_NAME, key, defValue);
    }

    public static float getFloat(Context context, String sp_name, String key, float defValue) {
        sp = context.getSharedPreferences(sp_name, 0);
        return sp.getFloat(key, defValue);
    }

    /**
     * 获取布尔值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getBoolean(context, SP_NAME, key, defValue);
    }

    public static boolean getBoolean(Context context, String sp_name, String key, boolean defValue) {
        sp = context.getSharedPreferences(sp_name, 0);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 删除值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        remove(context, SP_NAME, key);
    }

    public static void remove(Context context, String sp_name, String key) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().remove(key).commit();
    }

    /**
     * 清除数据
     *
     * @param context
     * @param sp_name
     */
    public static void clear(Context context, String sp_name) {
        sp = context.getSharedPreferences(sp_name, 0);
        sp.edit().clear().commit();
    }

    public static void clear(Context context) {
        clear(context, SP_NAME);
    }


    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
     */
    public static void saveObject(Context context,String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, 0);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static  <T> T getObject(Context context,  String key, Class<T> clazz) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, 0);
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
