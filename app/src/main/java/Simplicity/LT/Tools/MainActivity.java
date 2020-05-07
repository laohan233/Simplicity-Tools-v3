package Simplicity.LT.Tools;

import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import miui.app.AlertDialog;

import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.SpannableString;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import miui.preference.PreferenceActivity;
import miui.R;

/**
 *
 * */

public class MainActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    private long exitTime;
    private ListPreference gjcq = null;
    private static final String gjcq_main = "gjcq";

    private ListPreference sjys = null;
    private static final String sjys_main = "sjys";

    private ListPreference xtdh = null;
    private static final String xtdh_main = "xtdh";

    private ListPreference tqbj = null;
    private static final String tqbj_main = "tqbj";

    private ListPreference xmqh = null;
    private static final String xmqh_main = "xmqh";

    private ListPreference sim = null;
    private static final String sim_main = "sim";


    private AlertDialog alertDialog3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getDarkModeStatus(this)) {
            setTheme(R.style.Theme_Dark_Settings);super.onCreate(savedInstanceState);
        }else {
            setTheme(R.style.Theme_Light_Settings);super.onCreate(savedInstanceState);
        }
        addPreferencesFromResource(Simplicity.LT.Tools.R.xml.miui);
        try {


            if ((new File("/data/system/Simplicity/data/icon.sh").exists())) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /data\nmount -o rw,remount /system\nmount -o rw,remount /system_root/system\nchmod -R 0777 /system/Simplicity\nchmod -R 0777 /system/Simplicity/res\nchmod -R 0777 /system/Simplicity/tools\ncp -r -f /system/Simplicity/res/* /\nchmod -R 0777 /res_*"};
                ShellUtils.execCommand(commad, true);
            } else {
                    String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /data\nmount -o rw,remount /system\nmount -o rw,remount /system_root/system\nchmod -R 0777 /system/Simplicity\nchmod -R 0777 /system/Simplicity/res\nchmod -R 0777 /system/Simplicity/tools\ncp -r -f /system/Simplicity/res/* /\nchmod -R 0777 /res_*"};
                    ShellUtils.execCommand(commad, true);
                    String[] commands = new String[]{"mkdir -p /data/system/Simplicity/data","cp /system/Simplicity/tools/icon /data/system/Simplicity/data/icon.sh","chmod -R 0777 /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace(
            );
        }



        setsummary();status();sign();checkApk();



    }


    public static boolean getDarkModeStatus(Context context) {
        int mode = context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return mode == Configuration.UI_MODE_NIGHT_YES;
    }

    public boolean onPreferenceChange(Preference preference, Object objValue) {


        if (preference == gjcq) {
            if (objValue.equals("1")) {
                String[] commad = new String[]{"reboot"};
                ShellUtils.execCommand(commad, true);
                /* gjcq.setSummary("上次执行 :正常重启");*/
            }
            if (objValue.equals("2")) {
                String[] commad = new String[]{"reboot recovery"};
                ShellUtils.execCommand(commad, true);
                /*  gjcq.setSummary("上次执行 :卡刷模式");*/
            }
            if (objValue.equals("3")) {
                String[] commad = new String[]{"reboot bootloader"};
                ShellUtils.execCommand(commad, true);
                /* gjcq.setSummary("上次执行 :线刷模式");*/
            }
            if (objValue.equals("4")) {
                String[] commad = new String[]{"/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                /*  gjcq.setSummary("上次执行 :重启布局");*/
            }
        }

        if (preference == sjys) {
            if (objValue.equals("1")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_mr /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("默认样式");
            }
            if (objValue.equals("2")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_3 /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("星期 时间");
            }

            if (objValue.equals("3")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_2 /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("农历 时间");
            }

            if (objValue.equals("4")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_4 /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("年-月-日 星期 时间");
            }
            if (objValue.equals("5")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_5 /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("农历 年-月-日 星期 时间");
            }
            if (objValue.equals("6")) {
                String[] commad = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/media/theme/default/framework-miui-res\ncp /system/Simplicity/tools/framework-miui-res_1 /system/media/theme/default/framework-miui-res\nchmod -R 0644 /system/media/theme/default/framework-miui-res\n/system/Simplicity/tools/busybox killall com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                ShellUtils.execCommand(commad, true);
                sjys.setSummary("农历 星期 时间");
            }
        }

        if (preference == xtdh) {

            if ((!new File("/system/framework/framework-res.apk.bak").exists())) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk /system/framework/framework-res.apk.bak", "chmod -R 0777 /system/framework/framework-res.apk.bak", "sync"};
                ShellUtils.execCommand(a, true);
            }

            if ((!new File("/res_3D").exists()) || (!new File("/res_FlyLFlyQ").exists()) || (!new File("/res_SuperX").exists()) || (!new File("/res_IOS11One").exists()) || (!new File("/res_center").exists())) {
                String[] commands = new String[]{"chmod -R 0777 /system/res", "cp -r -f /system/res/* /", "chmod -R 0777 /res_*", "sync"};
                ShellUtils.execCommand(commands, true);
            }

            if (objValue.equals("1")) {
                if ((new File("/system/framework/framework-res.apk.bak").exists())) {
                    String[] a = new String[]{"chmod -R 0777 /system/framework/framework-res.apk", "rm -rf /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "chmod -R 0644 /system/framework/framework-res.apk"};
                    ShellUtils.execCommand(a, true);
                    reboot();
                } else {
                    Toast.makeText(MainActivity.this, "当前已是官方默认动画", Toast.LENGTH_SHORT).show();
                }
                xtdh.setSummary(" 官方默认");
            }
            if (objValue.equals("2")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_IOS11One /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_IOS11One", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary("极速残影");
                reboot();
            }

            if (objValue.equals("3")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_IOS /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_IOS", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary("滑入滑出");
                reboot();
            }

            if (objValue.equals("4")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_3D /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_3D", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary("3D特效");
                reboot();
            }

            if (objValue.equals("5")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_DouDong /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_DouDong", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary("抖动特效");
                reboot();
            }

            if (objValue.equals("6")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_DX8 /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_DX8", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary("DX8特效");
                reboot();
            }

            if (objValue.equals("7")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_FlyLFlyQ /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_FlyLFlyQ", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 飞来飞去");
                reboot();
            }

            if (objValue.equals("8")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_NiName /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_NiName", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 匿名特效");
                reboot();
            }

            if (objValue.equals("9")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_PiaoLPiaoQ /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_PiaoLPiaoQ", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 飘来飘去");
                reboot();
            }

            if (objValue.equals("10")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_SuperX /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_SuperX", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 超炫特效");
                reboot();
            }

            if (objValue.equals("11")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_TanDong /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_TanDong", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 翻转特效");
                reboot();
            }

            if (objValue.equals("12")) {
                String[] a = new String[]{"cp /system/framework/framework-res.apk.bak /system/framework/framework-res.apk.bakgf", "rm -rf /res", "mv /res_UP /res", "/system/Simplicity/tools/zip -r /system/framework/framework-res.apk.bak /res", "mv /res /res_UP", "chmod -R 0777 /system/framework/framework-res.apk.bakgf", "mv /system/framework/framework-res.apk.bak /system/framework/framework-res.apk", "mv /system/framework/framework-res.apk.bakgf /system/framework/framework-res.apk.bak", "chmod -R 0644 /system/framework/framework-res.apk"};
                ShellUtils.execCommand(a, true);
                xtdh.setSummary(" 上中下特效");
                reboot();
            }

        }


        if (preference == tqbj) {
            if (objValue.equals("1")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_tq_sf /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_tq_sf","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                tqbj.setSummary("状态栏图标上方（横屏不完美，不限DPI）");
            }
            if (objValue.equals("2")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_tq_zc /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_tq_zc","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                tqbj.setSummary("状态栏图标左侧（横屏完美，需小DPI）");
            }
            if (objValue.equals("3")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_tq_gb /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_tq_gb","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                tqbj.setSummary("关闭状态栏天气");
            }
        }


        if (preference == sim) {
            if (objValue.equals("1")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_yc_k0 /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_yc_k0","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                sim.setSummary("全部显示");
            }
            if (objValue.equals("2")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_yc_k1 /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_yc_k1","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                sim.setSummary("仅隐藏SIM卡1");
            }
            if (objValue.equals("3")) {
                String[] commad = new String[]{"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","rm -rf /res","mv /res_yc_k2 /res","/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res","mv /res /res_yc_k2","chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk","/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);
                sim.setSummary("仅隐藏SIM卡2");
            }
        }
        if (preference == xmqh) {
            if (objValue.equals("1")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_mr /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/gfcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_mr",
                        "mv /classes3.dex /ztl_xm_cj/gfcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
            if (objValue.equals("2")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_mr_xm /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/xmcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_mr_xm",
                        "mv /classes3.dex /ztl_xm_cj/xmcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
            if (objValue.equals("3")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_jz_zc /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/gfcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_jz_zc",
                        "mv /classes3.dex /ztl_xm_cj/gfcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
            if (objValue.equals("4")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_jz_xm_zc /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/xmcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_jz_xm_zc",
                        "mv /classes3.dex /ztl_xm_cj/xmcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
            if (objValue.equals("5")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_jz_yc /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/gfcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_jz_yc",
                        "mv /classes3.dex /ztl_xm_cj/gfcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
            if (objValue.equals("6")) {
                String[] commad = new String[]{"mount -o rw,remount /",
                        "mount -o rw,remount /system",
                        "mount -o rw,remount /system_root/system",
                        "rm -rf /res",
                        "mv /ztl_jz_xm_yc /res",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /res",
                        "mv /ztl_xm_cj/xmcj.dex /classes3.dex",
                        "/system/Simplicity/tools/zip -d /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "/system/Simplicity/tools/zip -r /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk /classes3.dex",
                        "mv /res /ztl_jz_xm_yc",
                        "mv /classes3.dex /ztl_xm_cj/xmcj.dex",
                        "chmod -R 0644 /system/priv-app/MiuiSystemUI/MiuiSystemUI.apk",
                        "/system/Simplicity/tools/busybox killall com.android.systemui"};
                ShellUtils.execCommand(commad, true);

            }
        }
            return true;
        }






    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, android.preference.Preference preference) {

        all_mount();

        CheckBoxPreference hykg = (CheckBoxPreference) findPreference("hykg");
        CheckBoxPreference xptq = (CheckBoxPreference) findPreference("xptq");
        CheckBoxPreference jzcd = (CheckBoxPreference) findPreference("jzcd");
        CheckBoxPreference hide = (CheckBoxPreference) findPreference("hide");
        CheckBoxPreference ycnz = (CheckBoxPreference) findPreference("ycnz");
        CheckBoxPreference ycly = (CheckBoxPreference) findPreference("ycly");
        CheckBoxPreference ycej = (CheckBoxPreference) findPreference("ycej");
        CheckBoxPreference ycyl = (CheckBoxPreference) findPreference("ycyl");
        CheckBoxPreference ycfx = (CheckBoxPreference) findPreference("ycfx");
        CheckBoxPreference ycdw = (CheckBoxPreference) findPreference("ycdw");
        CheckBoxPreference ychd = (CheckBoxPreference) findPreference("ychd");
        CheckBoxPreference ggpb = (CheckBoxPreference) findPreference("ggpb");
        CheckBoxPreference yysd = (CheckBoxPreference) findPreference("yysd");
        CheckBoxPreference ycmg = (CheckBoxPreference) findPreference("ycmg");
        CheckBoxPreference ycmz = (CheckBoxPreference) findPreference("ycmz");
        CheckBoxPreference ycdl = (CheckBoxPreference) findPreference("ycdl");
        CheckBoxPreference dylj = (CheckBoxPreference) findPreference("dylj");
        CheckBoxPreference dydk = (CheckBoxPreference) findPreference("dydk");
        CheckBoxPreference ddl = (CheckBoxPreference) findPreference("ddl");

        try{
        if (preference.getKey() != null && preference.getKey().startsWith("sh /")) {
            ShellUtils.execCommand(preference.getKey(), true);
        }

        if (preference.getKey().equals("wifi")){
            try {
                Intent intent2=new Intent(MainActivity.this, WifiActivity.class); startActivity(intent2);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }
        }


            if (preference.getKey().equals("yydj")){
                Intent intent = new Intent(MainActivity.this, LiteActivity.class);
                startActivity(intent);
            }

        if (preference.getKey().equals("gw")){
            try {
                Uri uri = Uri.parse("http://www.lt2333.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "访问失败", Toast.LENGTH_SHORT).show();
            }
        }




            if (preference.getKey().equals("pmmd")){
                final EditText dpi = new EditText(MainActivity.this);
                dpi.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入220到560之间的数值");
                dpi.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("屏幕密度")
                        .setView(dpi)
                        .setCancelable(true)
                        .setPositiveButton("修改", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(dpi.getWindowToken(), 0);
                                String input = dpi.getText().toString();
                                dpi.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 220 || i > 560) {
                                        Toast.makeText(MainActivity.this, "请输入220到560之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String capacity = "wm density " + i;
                                        ShellUtils.execCommand(capacity, true);
                                        Toast.makeText(getApplicationContext(), "密度修改为: " + input, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "还原为官方默认", Toast.LENGTH_SHORT).show();
                                ShellUtils.execCommand("wm density reset", true);
                            }
                        })
                        .show();
            }


            if (preference.getKey().equals("wk")){
                try {
                    String[] commad = new String[]{"am broadcast --user 0 -a update_profile com.miui.powerkeeper/com.miui.powerkeeper.cloudcontrol.CloudUpdateReceiver"};
                    ShellUtils.execCommand(commad, true);
                    Toast.makeText(MainActivity.this, "开启成功，前往设置-电量和性能查看下吧", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "开启失败", Toast.LENGTH_SHORT).show();
                }
            }



            if (preference.getKey().equals("yjjj")){
                AlertDialog.Builder dialog = new AlertDialog.
                        Builder(MainActivity.this);
                dialog.setTitle("一键精简");
                dialog.setMessage("此操作不可逆袭，可覆盖完整包恢复");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定精简", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] a = new String[] { "rm -rf /system/app/VipAccount\nrm -rf /system/app/Stk\nrm -rf /system/app/AnalyticsCore\nrm -rf /system/app/BasicDreams\nrm -rf /system/app/BookmarkProvider\nrm -rf /system/app/BugReport\nrm -rf /system/app/BuiltInPrintService\nrm -rf /system/app/CaptivePortalLogin\nrm -rf /system/app/ConferenceDialer\nrm -rf /system/app/ConfURIDialer\nrm -rf /system/app/GameCenter\nrm -rf /system/app/GPSLogSave\nrm -rf /system/app/greenguard\nrm -rf /system/app/HTMLViewer\nrm -rf /system/app/KSICibaEngine\nrm -rf /system/app/LiveWallpapersPicker\nrm -rf /system/app/mab\nrm -rf /system/app/MetokNLP\nrm -rf /system/app/MiDrive\nrm -rf /system/app/MiWallpaper\nrm -rf /system/app/MSA\nrm -rf /system/app/PacProcessor\nrm -rf /system/app/PaymentService\nrm -rf /system/app/PhotoTable\nrm -rf /system/app/PrintRecommendationService\nrm -rf /system/app/PrintSpooler\nrm -rf /system/app/SYSOPT\nrm -rf /system/app/talkback\nrm -rf /system/app/TranslationService\nrm -rf /system/app/UpnpService\nrm -rf /system/app/Userguide\nrm -rf /system/app/WallpaperBackup\nrm -rf /system/app/XMPass\nrm -rf /system/app/YouDaoEngine\nrm -rf /system/priv-app/CallLogBackup\nrm -rf /system/priv-app/MiGameCenterSDKService\nrm -rf /system/priv-app/MiRcs\nrm -rf /system/priv-app/MiuiFreeformService\nrm -rf /system/priv-app/MiuiVideo\nrm -rf /system/priv-app/PicoTts\nrm -rf /system/priv-app/ProxyHandler\nrm -rf /system/priv-app/SharedStorageBackup\nrm -rf /system/priv-app/WallpaperCropper\nrm -rf /system/priv-app/Music" };
                        ShellUtils.execCommand(a, true);
                        status();reboot();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                dialog.show();
            }


            if (preference.getKey().equals("xfss")){
                AlertDialog.Builder dialog = new AlertDialog.
                        Builder(MainActivity.this);
                dialog.setTitle("修复搜索框显示异常");
                dialog.setCancelable(false);
                dialog.setPositiveButton("修复", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/dimen name=\"action_bar_default_height\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res"};
                        ShellUtils.execCommand(b, true);
                        reboot();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }

                });
                dialog.show();
            }

            if (preference.getKey().equals("glhy")){
                try {
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("me.piebridge.prevent", "me.piebridge.prevent.ui.PreventActivity");
                    intent.setComponent(comp);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "启动失败", Toast.LENGTH_SHORT).show();
                }
            }

            if (preference.getKey().equals("wx")){
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", "小乌龟LittleTurtle网络科技");
                cm.setPrimaryClip(mClipData);
                AlertDialog.Builder dialog = new AlertDialog.
                        Builder(MainActivity.this);
                dialog.setTitle("关注微信公众号");
                dialog.setCancelable(false);
                dialog.setMessage(
                        "微信号已复制，前往微信搜素");
                dialog.setPositiveButton("去微信", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        ComponentName comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setComponent(comp);
                        startActivity(intent);
                    }
                });
                dialog.show();
            }

            if (preference.getKey().equals("dcys")){
                try {
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.fuelgauge.BatteryIndicatorStyle");
                    intent.setComponent(comp);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "启动失败", Toast.LENGTH_SHORT).show();
                }
            }

            if (preference.getKey().equals("ca")){
                if (checkApkExist(this, "com.coolapk.market")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coolapk.com/u/883441")));
                    Toast.makeText(this,"乌堆小透明：靓仔，点个关注吧！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"本机未安装酷安应用",Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("http://www.coolapk.com/u/883441");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }

            if (preference.getKey().equals("dlwz")){
                final EditText dlwz = new EditText(MainActivity.this);
                dlwz.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入需要伪装的电量（1-100）");
                dlwz.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("电量伪装")
                        .setView(dlwz)
                        .setCancelable(true)
                        .setPositiveButton("伪装", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(dlwz.getWindowToken(), 0);
                                String input = dlwz.getText().toString();
                                dlwz.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String capacity = "dumpsys battery set level " + i;
                                        ShellUtils.execCommand(capacity, true);
                                        Toast.makeText(getApplicationContext(), "电量伪装为: " + input, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "还原为默认", Toast.LENGTH_SHORT).show();
                                ShellUtils.execCommand("dumpsys battery reset", true);
                            }
                        })
                        .show();
            }


            if (preference.getKey().equals("zmbj_x")){
                final EditText zmbj_x = new EditText(MainActivity.this);
                zmbj_x.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）16:9/全面屏默认:4");
                zmbj_x.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("桌面布局-行-图标个数")
                        .setView(zmbj_x)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(zmbj_x.getWindowToken(), 0);
                                String input = zmbj_x.getText().toString();
                                zmbj_x.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_cell_count_x\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_cell_count_x\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面布局-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_cell_count_x\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_cell_count_x\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面布局-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }


            if (preference.getKey().equals("zmbj_y")){
                final EditText zmbj_y = new EditText(MainActivity.this);
                zmbj_y.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）16:9默认:5 全面屏默认:6");
                zmbj_y.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("桌面布局-列-图标个数")
                        .setView(zmbj_y)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(zmbj_y.getWindowToken(), 0);
                                String input = zmbj_y.getText().toString();
                                zmbj_y.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_cell_count_y\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_cell_count_y\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面布局-列-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_cell_count_y\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_cell_count_y\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面布局-列-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("szgs_12h")){
                final EditText szgs_12h = new EditText(MainActivity.this);
                SpannableString s = new SpannableString("请输入自定义时钟格式");
                szgs_12h.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("时钟格式-自定义-12小时制")
                        .setView(szgs_12h)
                        .setCancelable(true)
                        .setMessage(
                                "官方时钟组件不支持秒数显示\n" +
                                "y年   M月   d日\n" +
                                "h时在上午或下午(1~12)\n" +
                                "H时在一天中(0~23)\n" +
                                "m分   s秒   S毫秒   E星期\n" +
                                "D一年中的第几天\n" +
                                "F一月中第几个星期几\n" +
                                "w一年中第几个星期\n" +
                                "W一月中第几个星期\n" +
                                "a上午/下午标记符\n" +
                                "k时在一天中(1~24)\n" +
                                "K时在上午或下午(0~11)\n" +
                                "z时区   N月e农历\n" +
                                "支持空格，中文及- / \\等分隔符\n" +
                                "注：使用/或\\时需要在前加\\\n" +
                                "例：输入\\/ 状态栏则显示为 /\n" +
                                "输入\\\\ 状态栏则显示为 \\\n\n" +
                                "例如：N月e yyyy-MM-dd E aah:mm\n" +
                                "对应：七月十九 2019-08-19 周一 晚上8:50")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(szgs_12h.getWindowToken(), 0);
                                String input = szgs_12h.getText().toString();
                                szgs_12h.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                } catch (NumberFormatException e) {
                                    if ((new File("/system/media/theme/default/framework-miui-res").exists())) {
                                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_12hour_minute_pm\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                        ShellUtils.execCommand(a, true);
                                        String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <string name=\\\"fmt_time_12hour_minute_pm\\\">" + input +"<\\/string>/\" /data/system/Simplicity/res/framework-miui/theme_values.xml";
                                        ShellUtils.execCommand(capacity, true);
                                        String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                        ShellUtils.execCommand(b, true);
                                        Toast.makeText(getApplicationContext(), "时钟格式-12小时制 设置为: " + input, Toast.LENGTH_SHORT).show();
                                    } else {
                                        String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/framework-miui-res /system/media/theme/default/framework-miui-res","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_12hour_minute_pm\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                        ShellUtils.execCommand(a, true);
                                        String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <string name=\\\"fmt_time_12hour_minute_pm\\\">" + input +"<\\/string>/\" /data/system/Simplicity/res/framework-miui/theme_values.xml";
                                        ShellUtils.execCommand(capacity, true);
                                        String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                        ShellUtils.execCommand(b, true);
                                        Toast.makeText(getApplicationContext(), "时钟格式-12小时制 设置为: " + input, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_12hour_minute_pm\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);
                                String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "时钟格式-12小时制 设置为:默认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("szgs_24h")){
                final EditText szgs_24h = new EditText(MainActivity.this);
                SpannableString s = new SpannableString("请输入自定义时钟格式");
                szgs_24h.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("时钟格式-自定义-24小时制")
                        .setView(szgs_24h)
                        .setCancelable(true)
                        .setMessage(
                                "官方时钟组件不支持秒数显示\n" +
                                        "y年   M月   d日\n" +
                                        "h时在上午或下午(1~12)\n" +
                                        "H时在一天中(0~23)\n" +
                                        "m分   s秒   S毫秒   E星期\n" +
                                        "D一年中的第几天\n" +
                                        "F一月中第几个星期几\n" +
                                        "w一年中第几个星期\n" +
                                        "W一月中第几个星期\n" +
                                        "a上午/下午标记符\n" +
                                        "k时在一天中(1~24)\n" +
                                        "K时在上午或下午(0~11)\n" +
                                        "z时区   N月e农历\n" +
                                        "支持空格，中文及- / \\等分隔符\n" +
                                        "注：使用/或\\时需要在前加\\\n" +
                                        "例：输入\\/ 状态栏则显示为 /\n" +
                                        "输入\\\\ 状态栏则显示为 \\\n\n" +
                                        "例如：N月e yyyy-MM-dd E aah:mm\n" +
                                        "对应：七月十九 2019-08-19 周一 晚上8:50")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(szgs_24h.getWindowToken(), 0);
                                String input = szgs_24h.getText().toString();
                                szgs_24h.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {

                                    }
                                } catch (NumberFormatException e) {
                                    if ((new File("/system/media/theme/default/framework-miui-res").exists())) {
                                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_24hour_minute\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                        ShellUtils.execCommand(a, true);
                                        String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <string name=\\\"fmt_time_24hour_minute\\\">" + input +"<\\/string>/\" /data/system/Simplicity/res/framework-miui/theme_values.xml";
                                        ShellUtils.execCommand(capacity, true);
                                        String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                        ShellUtils.execCommand(b, true);
                                        Toast.makeText(getApplicationContext(), "时钟格式-24小时制 设置为: " + input, Toast.LENGTH_SHORT).show();
                                    } else {
                                        String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/framework-miui-res /system/media/theme/default/framework-miui-res","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_24hour_minute\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                        ShellUtils.execCommand(a, true);
                                        String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <string name=\\\"fmt_time_24hour_minute\\\">" + input +"<\\/string>/\" /data/system/Simplicity/res/framework-miui/theme_values.xml";
                                        ShellUtils.execCommand(capacity, true);
                                        String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                        ShellUtils.execCommand(b, true);
                                        Toast.makeText(getApplicationContext(), "时钟格式-24小时制 设置为: " + input, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/res","mkdir -p /data/system/Simplicity/res","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/res /system/media/theme/default/framework-miui-res","/system/Simplicity/tools/busybox sed -i '/string name=\"fmt_time_24hour_minute\"/d' /data/system/Simplicity/res/framework-miui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);
                                String[] b = new String[] {"cd /data/system/Simplicity/res","/system/Simplicity/tools/zip -r /system/media/theme/default/framework-miui-res.zip ./*","mv /system/media/theme/default/framework-miui-res.zip /system/media/theme/default/framework-miui-res","chmod -R 0644 /system/media/theme/default/framework-miui-res","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/framework-miui-res /data/system/Simplicity/bak/framework-miui-res","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "时钟格式-24小时制 设置为:默认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("ctbj_x")){
                final EditText ctbj_x = new EditText(MainActivity.this);
                ctbj_x.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）默认:4");
                ctbj_x.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("磁贴展开布局-行-图标个数")
                        .setView(ctbj_x)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(ctbj_x.getWindowToken(), 0);
                                String input = ctbj_x.getText().toString();
                                ctbj_x.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_columns\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_num_columns\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴展开布局-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_columns\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_num_columns\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴展开布局-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_columns\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);
                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("ctbj_y")){
                final EditText ctbj_y = new EditText(MainActivity.this);
                ctbj_y.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）默认:3");
                ctbj_y.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("磁贴展开布局-列-图标个数")
                        .setView(ctbj_y)
                        .setCancelable(true)
                        .setMessage("横屏时会存在BUG，可点击左下角“还原”恢复")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(ctbj_y.getWindowToken(), 0);
                                String input = ctbj_y.getText().toString();
                                ctbj_y.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_rows\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_num_rows\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴展开布局-列-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_rows\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_num_rows\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴展开布局-列-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_num_rows\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);
                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                            }
                        })

                        .show();
            }


            if (preference.getKey().equals("wjj")){
                final EditText wjj = new EditText(MainActivity.this);
                wjj.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）默认:3");
                wjj.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("桌面文件夹-行-图标个数")
                        .setView(wjj)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(wjj.getWindowToken(), 0);
                                String input = wjj.getText().toString();
                                wjj.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_folder_columns_count\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_folder_columns_count\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面文件夹-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/integer name=\"config_folder_columns_count\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"config_folder_columns_count\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面文件夹-行-图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }


            if (preference.getKey().equals("tbdx")){
                final EditText tbdx = new EditText(MainActivity.this);
                tbdx.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）");
                tbdx.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("桌面图标大小（单位：dp）")
                        .setView(tbdx)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(tbdx.getWindowToken(), 0);
                                String input = tbdx.getText().toString();
                                tbdx.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_height\"/d' /data/system/Simplicity/home/theme_values.xml","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_width\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"config_icon_height\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String capacity2 = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"config_icon_width\\\">" + i + "dp" + "<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity2, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面图标大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_height\"/d' /data/system/Simplicity/home/theme_values.xml","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_width\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"config_icon_height\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String capacity2 = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"config_icon_width\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml";
                                            ShellUtils.execCommand(capacity2, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "桌面图标大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_height\"/d' /data/system/Simplicity/home/theme_values.xml","/system/Simplicity/tools/busybox sed -i '/dimen name=\"config_icon_width\"/d' /data/system/Simplicity/home/theme_values.xml"};
                                ShellUtils.execCommand(a, true);
                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "已恢复默认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("ksct")){
                final EditText ksct = new EditText(MainActivity.this);
                ksct.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）默认:5");
                ksct.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("磁贴未展开布局-行图标个数")
                        .setView(ksct)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(ksct.getWindowToken(), 0);
                                String input = ksct.getText().toString();
                                ksct.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_qqs_count\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_qqs_count\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴未展开布局-行图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/integer name=\"quick_settings_qqs_count\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <integer name=\\\"quick_settings_qqs_count\\\">" + i +"<\\/integer>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "磁贴未展开布局-行图标个数 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }


            if (preference.getKey().equals("jz")){
                try {
                    Uri uri = Uri.parse("https://www.lt2333.com/#jz");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "访问失败", Toast.LENGTH_SHORT).show();
                }
            }

            if (preference.getKey().equals("dcjz")){
                try {
                    AlertDialog.Builder dialog = new AlertDialog.
                            Builder(MainActivity.this);
                    dialog.setTitle("提示");
                    dialog.setMessage("请充电到100%再进行校准");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确定校准", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String[] a = new String[] {""};
                            ShellUtils.execCommand(a, true);
                            status();reboot();
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }

                    });
                    dialog.show();
                } catch (Exception e) {

                }
            }


            if (preference.getKey().equals("tzrz")){
                try {
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationStationActivity");
                    intent.setComponent(comp);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "启动失败", Toast.LENGTH_SHORT).show();
                }
            }

            if (preference.getKey().equals("hdqx")){
                try {
                    Intent intent = new Intent();
                    ComponentName comp = new ComponentName("com.miui.securitycenter", "com.miui.powercenter.batteryhistory.BatteryHistoryDetailActivity");
                    intent.setComponent(comp);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "启动失败", Toast.LENGTH_SHORT).show();
                }
            }

            if (preference.getKey().equals("syxz")){
                try {

                    AlertDialog.Builder dialog = new AlertDialog.
                            Builder(MainActivity.this);
                    dialog.setTitle("使用须知&帮助");
                    dialog.setMessage(
                            "如遇到无法生效的情况，请到\n" +
                                    "【Magisk Manager-设置-开启Magisk核心功能模式】\n" +
                                    "重启后重新进行操作。\n" +
                                    "操作完成后可关闭Magisk核心功能模式。\n" +
                                    "\n时间居中/天气布局/系统动画/广告屏蔽/息屏天气\n" +
                                    "更新系统后需重新设置，其余功能不重置");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("我知道了", new DialogInterface.
                            OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.show();

                } catch (Exception e) {

                }
            }


            if (preference.getKey().equals("ycnz")){
                ycnz.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycnz_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycnz_on","echo 1 >/data/system/Simplicity/data/ycnz_off","/system/Simplicity/tools/busybox sed -i 's/,alarm_clock//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycnz_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycnz_off","echo 1 >/data/system/Simplicity/data/ycnz_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,alarm_clock/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycnz_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,alarm_clock/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ycly")){
                ycly.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycly_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycly_on","echo 1 >/data/system/Simplicity/data/ycly_off","/system/Simplicity/tools/busybox sed -i 's/,bluetooth//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycly_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycly_off","echo 1 >/data/system/Simplicity/data/ycly_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,bluetooth/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycly_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,bluetooth/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }


            if (preference.getKey().equals("ycej")){
                ycej.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycej_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycej_on","echo 1 >/data/system/Simplicity/data/ycej_off","/system/Simplicity/tools/busybox sed -i 's/,headset//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycej_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycej_off","echo 1 >/data/system/Simplicity/data/ycej_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,headset/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycej_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,headset/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ycyl")){
                ycyl.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycyl_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycyl_on","echo 1 >/data/system/Simplicity/data/ycyl_off","/system/Simplicity/tools/busybox sed -i 's/,volume//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycyl_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycyl_off","echo 1 >/data/system/Simplicity/data/ycyl_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,volume/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycyl_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,volume/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ycfx")){
                ycfx.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycfx_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycfx_on","echo 1 >/data/system/Simplicity/data/ycfx_off","/system/Simplicity/tools/busybox sed -i 's/,airplane//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycfx_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycfx_off","echo 1 >/data/system/Simplicity/data/ycfx_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,airplane/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycfx_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,airplane/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ycdw")){
                ycdw.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycdw_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdw_on","echo 1 >/data/system/Simplicity/data/ycdw_off","/system/Simplicity/tools/busybox sed -i 's/,location//g' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycdw_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdw_off","echo 1 >/data/system/Simplicity/data/ycdw_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,location/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycdw_on","/system/Simplicity/tools/busybox sed -i 's/settings put secure icon_blacklist rotate/&,location/' /data/system/Simplicity/data/icon.sh","sh /data/system/Simplicity/data/icon.sh"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("dylj")){
                dylj.setChecked(false);
                if ((new File("/data/system/Simplicity/data/dylj_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/dylj_on","echo 1 >/data/system/Simplicity/data/dylj_off","cp /system/Simplicity/tools/charging /system/media/audio/ui/charging.ogg","chmod 0644 /system/media/audio/ui/charging.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/charging.ogg /data/system/Simplicity/media/charging.ogg"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/dylj_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/dylj_off","echo 1 >/data/system/Simplicity/data/dylj_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/charging.ogg","chmod 0644 /system/media/audio/ui/charging.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/charging.ogg /data/system/Simplicity/media/charging.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/dylj_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/charging.ogg","chmod 0644 /system/media/audio/ui/charging.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/charging.ogg /data/system/Simplicity/media/charging.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }


            if (preference.getKey().equals("dydk")){
                dydk.setChecked(false);
                if ((new File("/data/system/Simplicity/data/dydk_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/dydk_on","echo 1 >/data/system/Simplicity/data/dydk_off","cp /system/Simplicity/tools/disconnect /system/media/audio/ui/disconnect.ogg","chmod 0644 /system/media/audio/ui/disconnect.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/disconnect.ogg /data/system/Simplicity/media/disconnect.ogg"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/dydk_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/dydk_off","echo 1 >/data/system/Simplicity/data/dydk_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/disconnect.ogg","chmod 0644 /system/media/audio/ui/disconnect.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/disconnect.ogg /data/system/Simplicity/media/disconnect.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/dydk_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/disconnect.ogg","chmod 0644 /system/media/audio/ui/disconnect.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/disconnect.ogg /data/system/Simplicity/media/disconnect.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ddl")){
                ddl.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ddl_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ddl_on","echo 1 >/data/system/Simplicity/data/ddl_off","cp /system/Simplicity/tools/LowBattery /system/media/audio/ui/LowBattery.ogg","chmod 0644 /system/media/audio/ui/LowBattery.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/LowBattery.ogg /data/system/Simplicity/media/LowBattery.ogg"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ddl_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ddl_off","echo 1 >/data/system/Simplicity/data/ddl_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/LowBattery.ogg","chmod 0644 /system/media/audio/ui/LowBattery.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/LowBattery.ogg /data/system/Simplicity/media/LowBattery.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ddl_on","cp /system/Simplicity/tools/0ogg /system/media/audio/ui/LowBattery.ogg","chmod 0644 /system/media/audio/ui/LowBattery.ogg","mkdir -p /data/system/Simplicity/media","cp /system/media/audio/ui/LowBattery.ogg /data/system/Simplicity/media/LowBattery.ogg"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("ychd")){
                ychd.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ychd_on").exists())) {
                    if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ychd_on","echo 1 >/data/system/Simplicity/data/ychd_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                        ShellUtils.execCommand(b, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ychd_on","echo 1 >/data/system/Simplicity/data/ychd_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                        ShellUtils.execCommand(b, true);
                        status();
                    }
                } else {
                    if ((new File("/data/system/Simplicity/data/ychd_off").exists())) {
                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ychd_off","echo 1 >/data/system/Simplicity/data/ychd_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <bool name=\\\"status_bar_hide_volte\\\">true<\\/bool>/\" /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ychd_off","echo 1 >/data/system/Simplicity/data/ychd_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <bool name=\\\"status_bar_hide_volte\\\">true<\\/bool>/\" /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }
                    } else {
                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ychd_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <bool name=\\\"status_bar_hide_volte\\\">true<\\/bool>/\" /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ychd_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/bool name=\"status_bar_hide_volte\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <bool name=\\\"status_bar_hide_volte\\\">true<\\/bool>/\" /data/system/Simplicity/ui/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }
                    }

                }
            }

            if (preference.getKey().equals("xlszdx")){
                final EditText xlszdx = new EditText(MainActivity.this);
                xlszdx.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）");
                xlszdx.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("下拉时钟大小（单位：dp）")
                        .setView(xlszdx)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(xlszdx.getWindowToken(), 0);
                                String input = xlszdx.getText().toString();
                                xlszdx.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"notch_expanded_clock_size\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "下拉通知栏时钟大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"notch_expanded_clock_size\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "下拉通知栏时钟大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);

                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "已恢复默认", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("ztlszdx")){
                final EditText ztlszdx = new EditText(MainActivity.this);
                ztlszdx.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）");
                ztlszdx.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("状态栏时钟大小（单位：sp）")
                        .setView(ztlszdx)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(ztlszdx.getWindowToken(), 0);
                                String input = ztlszdx.getText().toString();
                                ztlszdx.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"status_bar_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"status_bar_clock_size\\\">" + i + "sp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "状态栏时钟大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"status_bar_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"status_bar_clock_size\\\">" + i + "sp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "状态栏时钟大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"status_bar_clock_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);

                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "已恢复默认", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("ycmg")){
                ycmg.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycmg_on").exists())) {
                    String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmg_on","echo 1 >/data/system/Simplicity/data/ycmg_off", "/sbin/magiskhide --rm cmb.pb\n/sbin/magiskhide --rm com.chinamworld.main\n/sbin/magiskhide --rm com.unionpay\n/sbin/magiskhide --rm com.icbc\n/sbin/magiskhide --rm com.chinamworld.bocmbci\n/sbin/magiskhide --rm com.android.bankabc\n/sbin/magiskhide --rm com.cmbchina.ccd.pluto.cmbActivity\n/sbin/magiskhide --rm com.webank.wemoney\n/sbin/magiskhide --rm com.yitong.mbank.psbc\n/sbin/magiskhide --rm com.bankcomm.Bankcomm\n/sbin/magiskhide --rm com.citiccard.mobilebank\n/sbin/magiskhide --rm cn.com.spdb.mobilebank.per\n/sbin/magiskhide --rm com.ecitic.bank.mobile\n/sbin/magiskhide --rm com.forms\n/sbin/magiskhide --rm com.pingan.paces.ccms\n/sbin/magiskhide --rm com.cgbchina.xpt\n/sbin/magiskhide --rm com.ccb.companybank\n/sbin/magiskhide --rm com.miui.virtualsim\n/sbin/magiskhide --rm com.miui.virtualsim com.android.phone\n/sbin/magiskhide --rm com.miui.virtualsim com.miui.virtualsim:authentication\n/sbin/magiskhide --rm com.miui.virtualsim com.miui.virtualsim:pushservice"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    if ((new File("/data/system/Simplicity/data/ycmg_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmg_off","echo 1 >/data/system/Simplicity/data/ycmg_on", "/sbin/magiskhide --add cmb.pb\n/sbin/magiskhide --add com.chinamworld.main\n/sbin/magiskhide --add com.unionpay\n/sbin/magiskhide --add com.icbc\n/sbin/magiskhide --add com.chinamworld.bocmbci\n/sbin/magiskhide --add com.android.bankabc\n/sbin/magiskhide --add com.cmbchina.ccd.pluto.cmbActivity\n/sbin/magiskhide --add com.webank.wemoney\n/sbin/magiskhide --add com.yitong.mbank.psbc\n/sbin/magiskhide --add com.bankcomm.Bankcomm\n/sbin/magiskhide --add com.citiccard.mobilebank\n/sbin/magiskhide --add cn.com.spdb.mobilebank.per\n/sbin/magiskhide --add com.ecitic.bank.mobile\n/sbin/magiskhide --add com.forms\n/sbin/magiskhide --add com.pingan.paces.ccms\n/sbin/magiskhide --add com.cgbchina.xpt\n/sbin/magiskhide --add com.ccb.companybank\n/sbin/magiskhide --add com.miui.virtualsim\n/sbin/magiskhide --add com.miui.virtualsim com.android.phone\n/sbin/magiskhide --add com.miui.virtualsim com.miui.virtualsim:authentication\n/sbin/magiskhide --add com.miui.virtualsim com.miui.virtualsim:pushservice"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycmg_on","/sbin/magiskhide --add cmb.pb\n/sbin/magiskhide --add com.chinamworld.main\n/sbin/magiskhide --add com.unionpay\n/sbin/magiskhide --add com.icbc\n/sbin/magiskhide --add com.chinamworld.bocmbci\n/sbin/magiskhide --add com.android.bankabc\n/sbin/magiskhide --add com.cmbchina.ccd.pluto.cmbActivity\n/sbin/magiskhide --add com.webank.wemoney\n/sbin/magiskhide --add com.yitong.mbank.psbc\n/sbin/magiskhide --add com.bankcomm.Bankcomm\n/sbin/magiskhide --add com.citiccard.mobilebank\n/sbin/magiskhide --add cn.com.spdb.mobilebank.per\n/sbin/magiskhide --add com.ecitic.bank.mobile\n/sbin/magiskhide --add com.forms\n/sbin/magiskhide --add com.pingan.paces.ccms\n/sbin/magiskhide --add com.cgbchina.xpt\n/sbin/magiskhide --add com.ccb.companybank\n/sbin/magiskhide --add com.miui.virtualsim\n/sbin/magiskhide --add com.miui.virtualsim com.android.phone\n/sbin/magiskhide --add com.miui.virtualsim com.miui.virtualsim:authentication\n/sbin/magiskhide --add com.miui.virtualsim com.miui.virtualsim:pushservice"};
                        ShellUtils.execCommand(commands, true);
                        status();
                    }
                }
            }


            if (preference.getKey().equals("ycmz")){
                ycmz.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycmz_on").exists())) {
                    if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmz_on","echo 1 >/data/system/Simplicity/data/ycmz_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                        ShellUtils.execCommand(b, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmz_on","echo 1 >/data/system/Simplicity/data/ycmz_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                        ShellUtils.execCommand(b, true);
                        status();
                    }
                } else {
                    if ((new File("/data/system/Simplicity/data/ycmz_off").exists())) {
                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmz_off","echo 1 >/data/system/Simplicity/data/ycmz_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"icon_title_margin_top\\\">99dip<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycmz_off","echo 1 >/data/system/Simplicity/data/ycmz_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"icon_title_margin_top\\\">99dip<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }
                    } else {
                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycmz_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"icon_title_margin_top\\\">99dip<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycmz_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"icon_title_margin_top\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"icon_title_margin_top\\\">99dip<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }

                    }

                }
            }


            if (preference.getKey().equals("ycdl")){
                ycdl.setChecked(false);
                if ((new File("/data/system/Simplicity/data/ycdl_on").exists())) {
                    if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdl_on","echo 1 >/data/system/Simplicity/data/ycdl_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                        ShellUtils.execCommand(b, true);
                        status();
                    } else {
                        String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdl_on","echo 1 >/data/system/Simplicity/data/ycdl_off"};
                        ShellUtils.execCommand(commands, true);
                        String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                        ShellUtils.execCommand(a, true);
                        String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                        ShellUtils.execCommand(b, true);
                        status();
                    }
                } else {
                    if ((new File("/data/system/Simplicity/data/ycdl_off").exists())) {
                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdl_off","echo 1 >/data/system/Simplicity/data/ycdl_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"hotseats_height\\\">0dp<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","rm -rf /data/system/Simplicity/data/ycdl_off","echo 1 >/data/system/Simplicity/data/ycdl_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"hotseats_height\\\">0dp<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }
                    } else {
                        if ((new File("/system/media/theme/default/com.miui.home").exists())) {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycdl_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"hotseats_height\\\">0dp<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        } else {
                            String[] commands = new String[]{"mkdir /data/system/Simplicity/data", "chmod 777 /data/system/Simplicity/data","echo 1 >/data/system/Simplicity/data/ycdl_on"};
                            ShellUtils.execCommand(commands, true);
                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.miui.home /system/media/theme/default/com.miui.home","rm -rf /data/system/Simplicity/home","mkdir -p /data/system/Simplicity/home","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/home /system/media/theme/default/com.miui.home","/system/Simplicity/tools/busybox sed -i '/dimen name=\"hotseats_height\"/d' /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(a, true);
                            String[] commands2 = new String[]{"/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"hotseats_height\\\">0dp<\\/dimen>/\" /data/system/Simplicity/home/theme_values.xml"};
                            ShellUtils.execCommand(commands2, true);
                            String[] b = new String[] {"/system/Simplicit y/tools/zip -r /system/media/theme/default/com.miui.home -j /data/system/Simplicity/home/*","chmod -R 0644 /system/media/theme/default/com.miui.home","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.miui.home /data/system/Simplicity/bak/com.miui.home","/system/Simplicity/tools/busybox killall com.miui.home"};
                            ShellUtils.execCommand(b, true);
                            status();
                        }

                    }

                }
            }


            if (preference.getKey().equals("ggpb")){
                ggpb.setChecked(false);
                if ((new File("/system/Simplicity/data/ggpb_on").exists())) {
                    String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/ggpb_on","echo 1 >/system/Simplicity/data/ggpb_off", "mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/etc/hosts\ncp /system/Simplicity/tools/AdAway_OFF /system/etc/hosts\nchmod -R 0644 /system/etc/hosts"};
                    ShellUtils.execCommand(commands, true);
                    status();reboot();
                } else {
                    if ((new File("/system/Simplicity/data/ggpb_off").exists())) {
                        //
                        String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/ggpb_off","echo 1 >/system/Simplicity/data/ggpb_on", "mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/etc/hosts\ncp /system/Simplicity/tools/AdAway_ON /system/etc/hosts\nchmod -R 0644 /system/etc/hosts"};
                        ShellUtils.execCommand(commands, true);
                        status();reboot();
                    } else {
                        String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","echo 1 >/system/Simplicity/data/ggpb_on","mount -o rw,remount /\nmount -o rw,remount /system\nrm -rf /system/etc/hosts\ncp /system/Simplicity/tools/AdAway_ON /system/etc/hosts\nchmod -R 0644 /system/etc/hosts"};
                        ShellUtils.execCommand(commands, true);
                        status();reboot();
                    }
                }
            }

            if (preference.getKey().equals("yysd")){
                yysd.setChecked(false);
                if ((new File("/system/app/MiuiSuperMarket/MiuiSuperMarket.apk").exists())) {
                    String[] commands = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nmv /system/app/MiuiSuperMarket/MiuiSuperMarket.apk /system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak\nchmod -R 0644 /system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak"};
                    ShellUtils.execCommand(commands, true);
                    status();reboot();
                } else {
                    if ((new File("/system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak").exists())) {
                        //
                        String[] commands = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nmv /system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak /system/app/MiuiSuperMarket/MiuiSuperMarket.apk\nchmod -R 0644 /system/app/MiuiSuperMarket/MiuiSuperMarket.apk"};
                        ShellUtils.execCommand(commands, true);
                        status();reboot();
                    } else {
                        String[] commands = new String[]{"mount -o rw,remount /\nmount -o rw,remount /system\nmv /system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak /system/app/MiuiSuperMarket/MiuiSuperMarket.apk\nchmod -R 0644 /system/app/MiuiSuperMarket/MiuiSuperMarket.apk"};
                        ShellUtils.execCommand(commands, true);
                        status();reboot();
                    }
                }
            }

        if (preference.getKey().equals("hide")){
            hide.setChecked(false);
            if ((new File("/system/Simplicity/data/hide_off").exists())) {
                String[] commands = new String[] { "mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/hide_off","echo 1 >/system/Simplicity/data/hide_on","settings put global policy_control immersive.status=*","sync" };
                ShellUtils.execCommand(commands, true);
                status();
            } else {
                if ((new File("/system/Simplicity/data/hide_on").exists())) {
                    String[] commands = new String[] { "mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/hide_on","echo 1 >/system/Simplicity/data/hide_off","settings put global policy_control null","sync" };
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    String[] commands = new String[] { "mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","echo 1 >/system/Simplicity/data/hide_on","settings put global policy_control immersive.status=*","sync" };
                    ShellUtils.execCommand(commands, true);
                    status();
                }
            }
        }

        if (preference.getKey().equals("jzcd")){
            jzcd.setChecked(false);
            if ((new File("/system/Simplicity/data/jzcd_on").exists())) {
                String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/jzcd_on","echo 1 >/system/Simplicity/data/jzcd_off", "dumpsys battery reset", "echo 1 > /sys/class/power_supply/battery/jzcd_enabled", "echo 0 > /sys/class/power_supply/battery/input_supend"};
                ShellUtils.execCommand(commands, true);
                status();
            } else {
                if ((new File("/system/Simplicity/data/jzcd_off").exists())) {
                    //
                    String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/jzcd_off","echo 1 >/system/Simplicity/data/jzcd_on", "dumpsys battery reset", "echo 0 > /sys/class/power_supply/battery/jzcd_enabled", "echo 1 > /sys/class/power_supply/battery/input_supend"};
                    ShellUtils.execCommand(commands, true);
                    status();
                } else {
                    String[] commands = new String[]{"mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","echo 1 >/system/Simplicity/data/jzcd_on","dumpsys battery reset", "echo 0 > /sys/class/power_supply/battery/jzcd_enabled", "echo 1 > /sys/class/power_supply/battery/input_supend"};
                    ShellUtils.execCommand(commands, true);
                    status();
                }
            }
        }

        if (preference.getKey().equals("qqq")){
            joinQQGroup("owdUmO3qcOX55kwO8HXnbY3C1xHep8OS");
        }



        if (preference.getKey().equals("hykg")){
            hykg.setChecked(false);
            if ((new File("/system/framework/services.jar.hy").exists())) {
                String[] a = new String[] { "mv /system/framework/services.jar /system/framework/services.jar.gf","mv /system/framework/services.jar.hy /system/framework/services.jar" };
                ShellUtils.execCommand(a, true);
                hykg.setChecked(false);
                status();reboot();
            } else {
                if ((new File("/system/framework/services.jar.gf").exists())) {
                    String[] a = new String[] { "mv /system/framework/services.jar /system/framework/services.jar.hy","mv /system/framework/services.jar.gf /system/framework/services.jar" };
                    ShellUtils.execCommand(a, true);
                    hykg.setChecked(true);
                    status();reboot();
                } else {
                    status();
                    bcz();
                }
            }
        }

            if (preference.getKey().equals("xptq")){
                xptq.setChecked(false);
                if ((new File("/system/Simplicity/data/xptq_on").exists())) {
                    String[] a = new String[] {"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/xptq_on","echo 1 >/system/Simplicity/data/xptq_off","rm -rf /res\nmv /res_xp_gb /res\n/system/Simplicity/tools/zip -r /system/priv-app/MiuiAod/MiuiAod.apk /res\nmv /res /res_xp_gb\nchmod -R 0644 /system/priv-app/MiuiAod/MiuiAod.apk\n/system/Simplicity/tools/busybox killall com.miui.aod"};
                    ShellUtils.execCommand(a, true);
                    xptq.setChecked(false);
                    status();
                } else {
                    if ((new File("/system/Simplicity/data/xptq_off").exists())) {
                        String[] a = new String[] {"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","rm -rf /system/Simplicity/data/xptq_off","echo 1 >/system/Simplicity/data/xptq_on","rm -rf /res\nmv /res_xp_dk /res\n/system/Simplicity/tools/zip -r /system/priv-app/MiuiAod/MiuiAod.apk /res\nmv /res /res_xp_dk\nchmod -R 0644 /system/priv-app/MiuiAod/MiuiAod.apk\n/system/Simplicity/tools/busybox killall com.miui.aod"};
                        ShellUtils.execCommand(a, true);
                        xptq.setChecked(true);
                        status();
                    } else {
                        String[] a = new String[] {"mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /system_root/system","mkdir /system/Simplicity/data", "chmod 777 /system/Simplicity/data","echo 1 >/system/Simplicity/data/xptq_on","rm -rf /res\nmv /res_xp_dk /res\n/system/Simplicity/tools/zip -r /system/priv-app/MiuiAod/MiuiAod.apk /res\nmv /res /res_xp_dk\nchmod -R 0644 /system/priv-app/MiuiAod/MiuiAod.apk\n/system/Simplicity/tools/busybox killall com.miui.aod"};
                        ShellUtils.execCommand(a, true);
                        xptq.setChecked(true);
                        status();
                    }
                }
            }

            if (preference.getKey().equals("cksd")){
                final EditText cksd = new EditText(MainActivity.this);
                cksd.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                SpannableString s = new SpannableString("整数时无需小数 | 默认:1 | 推荐:0.75");
                cksd.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("窗口动画缩放速度（单位：倍）")
                        .setView(cksd)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(cksd.getWindowToken(), 0);
                                String input = cksd.getText().toString();
                                cksd.setText("");
                                try {
                                    String capacity = "settings put global window_animation_scale " + input;
                                    ShellUtils.execCommand(capacity, true);
                                    Toast.makeText(getApplicationContext(), "窗口动画缩放速度 设置为: " + input, Toast.LENGTH_SHORT).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"settings put global window_animation_scale 1"};
                                ShellUtils.execCommand(a, true);
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("dhsf")){
                final EditText dhsf = new EditText(MainActivity.this);
                dhsf.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                SpannableString s = new SpannableString("整数时无需小数 | 默认:1 | 推荐:0.75");
                dhsf.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("过渡动画缩放速度（单位：倍）")
                        .setView(dhsf)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(dhsf.getWindowToken(), 0);
                                String input = dhsf.getText().toString();
                                dhsf.setText("");
                                try {
                                    String capacity = "settings put global transition_animation_scale " + input;
                                    ShellUtils.execCommand(capacity, true);
                                    Toast.makeText(getApplicationContext(), "过渡动画缩放速度 设置为: " + input, Toast.LENGTH_SHORT).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"settings put global transition_animation_scale 1"};
                                ShellUtils.execCommand(a, true);
                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("szsbj")){
                final EditText szsbj = new EditText(MainActivity.this);
                szsbj.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-1000）");
                szsbj.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("通知栏时钟上边距（单位：dp）")
                        .setView(szsbj)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(szsbj.getWindowToken(), 0);
                                String input = szsbj.getText().toString();
                                szsbj.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 1000) {
                                        Toast.makeText(MainActivity.this, "请输入1到1000之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_header_height\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"notch_expanded_header_height\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "通知栏时钟上边距 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_header_height\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"notch_expanded_header_height\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "通知栏时钟上边距 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"notch_expanded_header_height\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);

                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "已恢复默认", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("tqdx")){
                final EditText tqdx = new EditText(MainActivity.this);
                tqdx.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                SpannableString s = new SpannableString("请输入（1-100）");
                tqdx.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("通知栏日期&天气大小（单位：dp）")
                        .setView(tqdx)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(tqdx.getWindowToken(), 0);
                                String input = tqdx.getText().toString();
                                tqdx.setText("");
                                try {
                                    int i = Integer.parseInt(input);
                                    if (i < 1 || i > 100) {
                                        Toast.makeText(MainActivity.this, "请输入1到100之间的数值", Toast.LENGTH_SHORT).show();
                                    } else {
                                        if ((new File("/system/media/theme/default/com.android.systemui").exists())) {
                                            String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"expanded_weather_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"expanded_weather_size\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "通知栏日期&天气大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        } else {
                                            String[] a = new String[] {"mount -o rw,remount /system","cp /system/Simplicity/tools/com.android.systemui /system/media/theme/default/com.android.systemui","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"expanded_weather_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                            ShellUtils.execCommand(a, true);
                                            String capacity = "/system/Simplicity/tools/busybox sed -i \"s/<MIUI_Theme_Values>/&\\n    <dimen name=\\\"expanded_weather_size\\\">" + i + "dp" +"<\\/dimen>/\" /data/system/Simplicity/ui/theme_values.xml";
                                            ShellUtils.execCommand(capacity, true);
                                            String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                            ShellUtils.execCommand(b, true);
                                            Toast.makeText(getApplicationContext(), "通知栏日期&天气大小 设置为: " + input, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("恢复默认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"mount -o rw,remount /system","rm -rf /data/system/Simplicity/ui","mkdir -p /data/system/Simplicity/ui","/system/Simplicity/tools/unzip -o -d /data/system/Simplicity/ui /system/media/theme/default/com.android.systemui","/system/Simplicity/tools/busybox sed -i '/dimen name=\"expanded_weather_size\"/d' /data/system/Simplicity/ui/theme_values.xml"};
                                ShellUtils.execCommand(a, true);

                                String[] b = new String[] {"/system/Simplicity/tools/zip -r /system/media/theme/default/com.android.systemui -j /data/system/Simplicity/ui/*","chmod -R 0644 /system/media/theme/default/com.android.systemui","mkdir -p /data/system/Simplicity/bak","cp /system/media/theme/default/com.android.systemui /data/system/Simplicity/bak/com.android.systemui","/system/Simplicity/tools/busybox killall com.android.systemui"};
                                ShellUtils.execCommand(b, true);
                                Toast.makeText(getApplicationContext(), "已恢复默认", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            }

            if (preference.getKey().equals("scsf")){
                final EditText scsf = new EditText(MainActivity.this);
                scsf.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                SpannableString s = new SpannableString("整数时无需小数 | 默认:1 | 推荐:0.75");
                scsf.setHint(s);

                new android.app.AlertDialog.Builder(MainActivity.this)
                        .setTitle("Animator动画缩放速度（单位：倍）")
                        .setView(scsf)
                        .setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(scsf.getWindowToken(), 0);
                                String input = scsf.getText().toString();
                                scsf.setText("");
                                try {
                                    String capacity = "settings put global animator_duration_scale " + input;
                                    ShellUtils.execCommand(capacity, true);
                                    Toast.makeText(getApplicationContext(), "Animator动画缩放速度 设置为: " + input, Toast.LENGTH_SHORT).show();
                                } catch (NumberFormatException e) {
                                    Toast.makeText(MainActivity.this, "请重新输入", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })

                        .setNegativeButton("还原", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String[] a = new String[] {"settings put global animator_duration_scale 1"};
                                ShellUtils.execCommand(a, true);
                            }
                        })
                        .show();
            }



        }catch(Exception e){
            // 异常
        }
        return false;
    }
    private void bcz() {
        AlertDialog.Builder dialog = new AlertDialog.
                Builder(MainActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage(
                "配置文件不存在，当前版本暂不支持");
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.show();
    }
    private void setsummary() {

        findPreference("hide").setTitle("去通知栏");
        ((CheckBoxPreference) findPreference("hide")).setSummaryOff("去除通知栏 | 关");
        ((CheckBoxPreference) findPreference("hide")).setSummaryOn("去除通知栏 | 开");

        findPreference("jzcd").setTitle("禁止充电");
        ((CheckBoxPreference) findPreference("jzcd")).setSummaryOff("禁止手机充电 | 关");
        ((CheckBoxPreference) findPreference("jzcd")).setSummaryOn("禁止手机充电 | 开");



        findPreference("hykg").setTitle("黑域开关");
        ((CheckBoxPreference) findPreference("hykg")).setSummaryOff("控制后台 | 关");
        ((CheckBoxPreference) findPreference("hykg")).setSummaryOn("控制后台 | 开");

        findPreference("ycnz").setTitle("隐藏闹钟");
        ((CheckBoxPreference) findPreference("ycnz")).setSummaryOff("隐藏闹钟图标 | 关");
        ((CheckBoxPreference) findPreference("ycnz")).setSummaryOn("隐藏闹钟图标 | 开");

        findPreference("ycly").setTitle("隐藏蓝牙");
        ((CheckBoxPreference) findPreference("ycly")).setSummaryOff("隐藏蓝牙图标 | 关");
        ((CheckBoxPreference) findPreference("ycly")).setSummaryOn("隐藏蓝牙图标 | 开");

        findPreference("ycej").setTitle("隐藏耳机");
        ((CheckBoxPreference) findPreference("ycej")).setSummaryOff("隐藏耳机图标 | 关");
        ((CheckBoxPreference) findPreference("ycej")).setSummaryOn("隐藏耳机图标 | 开");

        findPreference("ycyl").setTitle("隐藏音量");
        ((CheckBoxPreference) findPreference("ycyl")).setSummaryOff("隐藏音量图标 | 关");
        ((CheckBoxPreference) findPreference("ycyl")).setSummaryOn("隐藏音量图标 | 开");

        findPreference("ycfx").setTitle("隐藏飞行");
        ((CheckBoxPreference) findPreference("ycfx")).setSummaryOff("隐藏飞行图标 | 关");
        ((CheckBoxPreference) findPreference("ycfx")).setSummaryOn("隐藏飞行图标 | 开");

        findPreference("ycdw").setTitle("隐藏定位");
        ((CheckBoxPreference) findPreference("ycdw")).setSummaryOff("隐藏定位图标 | 关");
        ((CheckBoxPreference) findPreference("ycdw")).setSummaryOn("隐藏定位图标 | 开");

        findPreference("ychd").setTitle("隐藏HD");
        ((CheckBoxPreference) findPreference("ychd")).setSummaryOff("隐藏HD图标 | 关");
        ((CheckBoxPreference) findPreference("ychd")).setSummaryOn("隐藏HD图标 | 开");

        findPreference("ggpb").setTitle("广告屏蔽");
        ((CheckBoxPreference) findPreference("ggpb")).setSummaryOff("通过Hosts屏蔽广告 | 关");
        ((CheckBoxPreference) findPreference("ggpb")).setSummaryOn("通过Hosts屏蔽广告 | 开");

        findPreference("yysd").setTitle("官方应用商店");
        ((CheckBoxPreference) findPreference("yysd")).setSummaryOff("官方应用商店 | 关");
        ((CheckBoxPreference) findPreference("yysd")).setSummaryOn("官方应用商店 | 开");

        findPreference("ycmg").setTitle("隐藏ROOT");
        ((CheckBoxPreference) findPreference("ycmg")).setSummaryOff("隐藏ROOT使用银行APP/一元流量等 | 关");
        ((CheckBoxPreference) findPreference("ycmg")).setSummaryOn("隐藏ROOT使用银行APP/一元流量等 | 开");

        findPreference("ycmz").setTitle("隐藏系统桌面应用名");
        ((CheckBoxPreference) findPreference("ycmz")).setSummaryOff("隐藏系统桌面应用名 | 关");
        ((CheckBoxPreference) findPreference("ycmz")).setSummaryOn("隐藏系统桌面应用名 | 开");

        findPreference("dylj").setTitle("禁用电源连接音效");
        ((CheckBoxPreference) findPreference("dylj")).setSummaryOff("禁用电源连接音效 | 关");
        ((CheckBoxPreference) findPreference("dylj")).setSummaryOn("禁用电源连接音效 | 开");

        findPreference("dydk").setTitle("禁用电源断开音效");
        ((CheckBoxPreference) findPreference("dydk")).setSummaryOff("禁用电源断开音效 | 关");
        ((CheckBoxPreference) findPreference("dydk")).setSummaryOn("禁用电源断开音效 | 开");

        findPreference("ddl").setTitle("禁用低电量提示音效");
        ((CheckBoxPreference) findPreference("ddl")).setSummaryOff("禁用低电量提示音效 | 关");
        ((CheckBoxPreference) findPreference("ddl")).setSummaryOn("禁用低电量提示音效 | 开");

        findPreference("ycdl").setTitle("隐藏系统桌面底栏");
        ((CheckBoxPreference) findPreference("ycdl")).setSummaryOff("移除底栏应用后开启 推荐4x8布局 | 关");
        ((CheckBoxPreference) findPreference("ycdl")).setSummaryOn("移除底栏应用后开启 推荐4x8布局 | 开");

        findPreference("xptq").setTitle("息屏天气");
        ((CheckBoxPreference) findPreference("xptq")).setSummaryOff("息屏显示天气 | 关");
        ((CheckBoxPreference) findPreference("xptq")).setSummaryOn("息屏显示天气 | 开");

        findPreference("xmqh").setTitle("显秒&布局切换");
        findPreference("xmqh").setSummary("切换时钟显示位置和时钟秒数显示");

        findPreference("wifi").setTitle("WIFI密码");
        findPreference("wifi").setSummary("查询已连接过的WIFI密码");

        findPreference("sjys").setTitle("时钟格式-预设");
        findPreference("sjys").setSummary("切换状态栏时钟格式");

        findPreference("qqq").setTitle("点此加入QQ群");
        findPreference("qqq").setSummary("QQ群 | 206256181");

        findPreference("ca").setTitle("点此关注开发者酷安");
        findPreference("ca").setSummary("酷安 | @乌堆小透明");

        findPreference("gw").setTitle("点此进入官网");
        findPreference("gw").setSummary("www.lt2333.com");

        findPreference("bbh").setTitle("Simplicity Tools 版本号");

        findPreference("ctbj_x").setTitle("磁贴展开-行-图标个数");
        findPreference("ctbj_x").setSummary("自定义快捷磁贴展开时-行-图标个数");

        findPreference("ctbj_y").setTitle("磁贴展开-列-图标个数");
        findPreference("ctbj_y").setSummary("自定义快捷磁贴展开时-列-图标个数");

        findPreference("ksct").setTitle("磁贴未展开-行-图标个数");
        findPreference("ksct").setSummary("自定义快捷磁贴未展开时-行-图标个数");

        findPreference("dcys").setTitle("电池样式");
        findPreference("dcys").setSummary("在刘海屏设备使用百分比外显");

        findPreference("pmmd").setTitle("屏幕密度");
        findPreference("pmmd").setSummary("更改屏幕密度（DPI）");

        findPreference("xtdh").setTitle("系统动画");
        findPreference("xtdh").setSummary("更改系统过渡动画");

        findPreference("wk").setTitle("激活温控开关");
        findPreference("wk").setSummary("激活系统设置-电池-温控开关");

        findPreference("gjcq").setTitle("高级重启");
        findPreference("gjcq").setSummary("软重启/界面/Recovery/FastBoot……");

        findPreference("yjjj").setTitle("一键精简");
        findPreference("yjjj").setSummary("此操作不可逆袭，可覆盖完整包恢复");

        findPreference("glhy").setTitle("管理黑域");
        findPreference("glhy").setSummary("管理黑域，设置黑域白/黑名单");

        findPreference("dlwz").setTitle("电量伪装");
        findPreference("dlwz").setSummary("和盆友借充电器，电量低的先充");

        findPreference("tzrz").setTitle("通知日志");
        findPreference("tzrz").setSummary("可检查MIPUSH是否正常推送");

        findPreference("hdqx").setTitle("耗电曲线");
        findPreference("hdqx").setSummary("快速查看电量折线图");

        findPreference("jz").setTitle("捐赠开发者");
        findPreference("jz").setSummary("请开发者喝杯咖啡，恰顿饭");

        findPreference("tqbj").setTitle("天气布局");
        findPreference("tqbj").setSummary("更改状态栏天气的布局");

        findPreference("dcjz").setTitle("电池校准");
        findPreference("dcjz").setSummary("通过删除batterystats.bin校准电池");

        findPreference("syxz").setTitle("使用须知&帮助");
        findPreference("syxz").setSummary("使用前需要知道的东西");

        findPreference("zmbj_x").setTitle("桌面布局-行-图标个数");
        findPreference("zmbj_x").setSummary("自定义桌面布局-行-图标个数");

        findPreference("zmbj_y").setTitle("桌面布局-列-图标个数");
        findPreference("zmbj_y").setSummary("自定义桌面布局-列-图标个数");

        findPreference("wjj").setTitle("桌面文件夹-行-图标个数");
        findPreference("wjj").setSummary("自定义桌面文件夹-行-图标个数");

        findPreference("yydj").setTitle("应用冻结");
        findPreference("yydj").setSummary("冻结已安装的应用程序");

        findPreference("szgs_12h").setTitle("时钟格式-自定义-12小时制");
        findPreference("szgs_12h").setSummary("自定义时钟格式-12小时制");

        findPreference("szgs_24h").setTitle("时钟格式-自定义-24小时制");
        findPreference("szgs_24h").setSummary("自定义时钟格式-24小时制");

        findPreference("sim").setTitle("隐藏SIM卡图标");
        findPreference("sim").setSummary("状态栏需使用官方主题（可混搭）");

        findPreference("cksd").setTitle("窗口动画缩放");
        findPreference("cksd").setSummary("自定义窗口动画缩放速度");

        findPreference("dhsf").setTitle("过渡动画缩放");
        findPreference("dhsf").setSummary("自定义过渡动画缩放速度");

        findPreference("scsf").setTitle("Animator时长缩放");
        findPreference("scsf").setSummary("自定义Animator时长缩放速度");

        findPreference("xfss").setTitle("修复搜索框显示异常");
        findPreference("xfss").setSummary("修复MIUI11搜索框显示异常");

        findPreference("wx").setTitle("点击关注微信公众号");
        findPreference("wx").setSummary("小乌龟LiitleTurtle网络科技");

        findPreference("tbdx").setTitle("桌面图标大小");
        findPreference("tbdx").setSummary("自定义桌面应用图标大小");

        findPreference("xlszdx").setTitle("下拉时钟大小");
        findPreference("xlszdx").setSummary("自定义下拉通知栏时钟大小");

        findPreference("ztlszdx").setTitle("状态栏时钟大小");
        findPreference("ztlszdx").setSummary("自定义状态栏时钟大小");

        findPreference("szsbj").setTitle("通知栏时钟上边距");
        findPreference("szsbj").setSummary("自定义通知栏时钟上边距");

        findPreference("tqdx").setTitle("通知栏日期&天气大小");
        findPreference("tqdx").setSummary("自定义通知栏日期&天气文字大小");
    }
    private void all_mount() {
        String[] commands = new String[] { "mount -o rw,remount /","mount -o rw,remount /system","mount -o rw,remount /vendor","mount -o rw,remount /vendor/etc","mount -o rw,remount /system/vendor/etc","mount -o rw,remount /system/system","mount -o rw,remount /system/etc","mount -o rw,remount /system_root/system","echo Simplicity >/system/test","echo Simplicity >/test","rm -rf /system/res/res_center/layout/readme.txt","sync" };
        ShellUtils.execCommand(commands, true);
    }
    private void status() {


        CheckBoxPreference hykg = (CheckBoxPreference) findPreference("hykg");
        CheckBoxPreference xptq = (CheckBoxPreference) findPreference("xptq");
        CheckBoxPreference jzcd = (CheckBoxPreference) findPreference("jzcd");
        CheckBoxPreference hide = (CheckBoxPreference) findPreference("hide");
        CheckBoxPreference ycnz = (CheckBoxPreference) findPreference("ycnz");
        CheckBoxPreference ycly = (CheckBoxPreference) findPreference("ycly");
        CheckBoxPreference ycej = (CheckBoxPreference) findPreference("ycej");
        CheckBoxPreference ycyl = (CheckBoxPreference) findPreference("ycyl");
        CheckBoxPreference ychd = (CheckBoxPreference) findPreference("ychd");
        CheckBoxPreference ycfx = (CheckBoxPreference) findPreference("ycfx");
        CheckBoxPreference ycdw = (CheckBoxPreference) findPreference("ycdw");
        CheckBoxPreference ggpb = (CheckBoxPreference) findPreference("ggpb");
        CheckBoxPreference yysd = (CheckBoxPreference) findPreference("yysd");
        CheckBoxPreference ycmg = (CheckBoxPreference) findPreference("ycmg");
        CheckBoxPreference ycmz = (CheckBoxPreference) findPreference("ycmz");
        CheckBoxPreference ycdl = (CheckBoxPreference) findPreference("ycdl");
        CheckBoxPreference dylj = (CheckBoxPreference) findPreference("dylj");
        CheckBoxPreference dydk = (CheckBoxPreference) findPreference("dydk");
        CheckBoxPreference ddl = (CheckBoxPreference) findPreference("ddl");

        gjcq = (ListPreference) findPreference(gjcq_main);
        gjcq.setOnPreferenceChangeListener(this);

        sjys = (ListPreference) findPreference(sjys_main);
        sjys.setOnPreferenceChangeListener(this);

        xtdh = (ListPreference) findPreference(xtdh_main);
        xtdh.setOnPreferenceChangeListener(this);

        tqbj = (ListPreference) findPreference(tqbj_main);
        tqbj.setOnPreferenceChangeListener(this);

        xmqh = (ListPreference) findPreference(xmqh_main);
        xmqh.setOnPreferenceChangeListener(this);

        sim = (ListPreference) findPreference(sim_main);
        sim.setOnPreferenceChangeListener(this);

        if ((new File("/system/Simplicity/data/jzcd_on").exists())) {
            jzcd.setChecked(true);
        } else {
            if ((new File("/system/Simplicity/data/jzcd_off").exists())) {
                jzcd.setChecked(false);
            } else {
                jzcd.setChecked(false);
            }
        }

        if ((new File("/system/Simplicity/data/xptq_on").exists())) {
            xptq.setChecked(true);
        } else {
            if ((new File("/system/Simplicity/data/xptq_off").exists())) {
                xptq.setChecked(false);
            } else {
                xptq.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycnz_on").exists())) {
            ycnz.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycnz_off").exists())) {
                ycnz.setChecked(false);
            } else {
                ycnz.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ychd_on").exists())) {
            ychd.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ychd_off").exists())) {
                ychd.setChecked(false);
            } else {
                ychd.setChecked(false);
            }
        }



        if ((new File("/data/system/Simplicity/data/dylj_on").exists())) {
            dylj.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/dylj_off").exists())) {
                dylj.setChecked(false);
            } else {
                dylj.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/dydk_on").exists())) {
            dydk.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/dydk_off").exists())) {
                dydk.setChecked(false);
            } else {
                dydk.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ddl_on").exists())) {
            ddl.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ddl_off").exists())) {
                ddl.setChecked(false);
            } else {
                ddl.setChecked(false);
            }
        }


        if ((new File("/data/system/Simplicity/data/ycly_on").exists())) {
            ycly.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycly_off").exists())) {
                ycly.setChecked(false);
            } else {
                ycly.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycyl_on").exists())) {
            ycyl.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycyl_off").exists())) {
                ycyl.setChecked(false);
            } else {
                ycyl.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycfx_on").exists())) {
            ycfx.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycfx_off").exists())) {
                ycfx.setChecked(false);
            } else {
                ycfx.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycdw_on").exists())) {
            ycdw.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycdw_off").exists())) {
                ycdw.setChecked(false);
            } else {
                ycdw.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycej_on").exists())) {
            ycej.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycej_off").exists())) {
                ycej.setChecked(false);
            } else {
                ycej.setChecked(false);
            }
        }

        if ((new File("/system/Simplicity/data/ggpb_on").exists())) {
            ggpb.setChecked(true);
        } else {
            if ((new File("/system/Simplicity/data/ggpb_off").exists())) {
                ggpb.setChecked(false);
            } else {
                ggpb.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycmz_on").exists())) {
            ycmz.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycmz_off").exists())) {
                ycmz.setChecked(false);
            } else {
                ycmz.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycdl_on").exists())) {
            ycdl.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycdl_off").exists())) {
                ycdl.setChecked(false);
            } else {
                ycdl.setChecked(false);
            }
        }

        if ((new File("/system/app/MiuiSuperMarket/MiuiSuperMarket.apk").exists())) {
            yysd.setChecked(true);
        } else {
            if ((new File("/system/app/MiuiSuperMarket/MiuiSuperMarket.apk.bak").exists())) {
                yysd.setChecked(false);
            } else {
                yysd.setChecked(false);
            }
        }

        if ((new File("/system/Simplicity/data/hide_on").exists())) {
            hide.setChecked(true);
        } else {
            if ((new File("/system/Simplicity/data/hide_off").exists())) {
                hide.setChecked(false);
            } else {
                hide.setChecked(false);
            }
        }

        if ((new File("/data/system/Simplicity/data/ycmg_on").exists())) {
            ycmg.setChecked(true);
        } else {
            if ((new File("/data/system/Simplicity/data/ycmg_off").exists())) {
                hide.setChecked(false);
            } else {
                hide.setChecked(false);
            }
        }


        if ((new File("/system/framework/services.jar.gf").exists())) {
            hykg.setChecked(true);
        } else {
            if ((new File("/system/framework/services.jar.hy").exists())) {
                hykg.setChecked(false);
            }
        }
    }
    private void reboot() {
        AlertDialog.Builder dialog = new AlertDialog.
                Builder(MainActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("重启手机后生效");
        dialog.setCancelable(false);
        dialog.setPositiveButton("立即重启", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String[] a = new String[] { "reboot" };
                ShellUtils.execCommand(a, true);
            }
        });
        dialog.setNegativeButton("稍后重启", new DialogInterface.
                OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "重启手机后生效", Toast.LENGTH_SHORT).show();
            }

        });
        dialog.show();
    }

    public boolean joinQQGroup(String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        try {
            startActivity(intent);
            return true;
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "未安装手Q或安装的版本不支持", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }



    public void sign(){
        SignCheck signCheck = new SignCheck(this, "78:E2:A3:25:2B:7B:FD:7B:2D:FB:07:CE:EC:C3:36:D7:ED:D3:E0:19");
        if (!signCheck.check()) {
            AlertDialog.Builder dialog = new AlertDialog.
                    Builder(MainActivity.this);
            dialog.setTitle("提示");
            dialog.setMessage("Simplicity Tools异常");
            dialog.setCancelable(false);
            dialog.setPositiveButton("退出", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            });
            dialog.show();
        }
    }

    public class SignCheck {
        private Context context;
        private String cer;
        private String realCer;

        public SignCheck(Context context, String realCer) {
            this.context = context;
            this.realCer = realCer;
            cer = null;
            this.cer = getCertificateSHA1Fingerprint();
        }

        public String getCertificateSHA1Fingerprint() {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            int flags = PackageManager.GET_SIGNATURES;
            PackageInfo packageInfo = null;
            try {
                packageInfo = pm.getPackageInfo(packageName, flags);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Signature[] signatures = packageInfo.signatures;
            byte[] cert = signatures[0].toByteArray();
            InputStream input = new ByteArrayInputStream(cert);
            CertificateFactory cf = null;
            try {
                cf = CertificateFactory.getInstance("X509");
            } catch (Exception e) {
                e.printStackTrace();
            }
            X509Certificate c = null;

            try {
                c = (X509Certificate) cf.generateCertificate(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String hexString = null;
            try {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                byte[] publicKey = md.digest(c.getEncoded());
                hexString = byte2HexFormatted(publicKey);
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
            } catch (CertificateEncodingException e) {
                e.printStackTrace();
            }
            return hexString;
        }
        private String byte2HexFormatted(byte[] arr) {

            StringBuilder str = new StringBuilder(arr.length * 2);

            for (int i = 0; i < arr.length; i++) {
                String h = Integer.toHexString(arr[i]);
                int l = h.length();
                if (l == 1)
                    h = "0" + h;
                if (l > 2)
                    h = h.substring(l - 2, l);
                str.append(h.toUpperCase());
                if (i < (arr.length - 1))
                    str.append(':');
            }
            return str.toString();
        }
        public boolean check() {

            if (this.realCer != null) {
                cer = cer.trim();
                realCer = realCer.trim();
                return this.cer.equals(this.realCer);
            } else {
                finish();
                System.exit(0);
            }
            return false;
        }
    }
    public void checkApk(){
        if (!checkApkExist(MainActivity.this, "Simplicity.LT.Tools")) {
            AlertDialog.Builder dialog = new AlertDialog.
                    Builder(MainActivity.this);
            dialog.setTitle("提示");
            dialog.setMessage("Simplicity Tools异常");
            dialog.setCancelable(false);
            dialog.setPositiveButton("退出", new DialogInterface.
                    OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            });
            dialog.show();
        }
    }

}