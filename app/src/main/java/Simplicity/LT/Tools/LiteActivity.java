package Simplicity.LT.Tools;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceScreen;
import android.text.SpannableString;
import android.text.method.DigitsKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import miui.R;
import miui.app.AlertDialog;
import miui.preference.PreferenceActivity;

public class LiteActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Light_Settings);super.onCreate(savedInstanceState);
        addPreferencesFromResource(Simplicity.LT.Tools.R.xml.lite);
        CheckRoot();mount();setsummary();
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, android.preference.Preference preference) {
        if (preference.getKey().equals("cust")) {
            final EditText dpi = new EditText(LiteActivity.this);
            SpannableString s = new SpannableString("请输入一个包名");
            dpi.setHint(s);
            new android.app.AlertDialog.Builder(LiteActivity.this)
                    .setTitle("自定义冻结")
                    .setView(dpi)
                    .setCancelable(true)
                    .setPositiveButton("冻结", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(dpi.getWindowToken(), 0);
                            String input = dpi.getText().toString();
                            dpi.setText("");
                            if (input.length() > 0) {
                                try {
                                    Toast.makeText(getApplicationContext(), "冻结应用: " + input, Toast.LENGTH_SHORT).show();
                                    String disable = "pm disable " + input;
                                    ShellUtils.execCommand(disable, true);
                                } catch (NumberFormatException e) {
                                    Toast.makeText(LiteActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LiteActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("解冻", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(dpi.getWindowToken(), 0);
                            String input = dpi.getText().toString();
                            dpi.setText("");
                            if (input.length() > 0) {
                                try {
                                    Toast.makeText(getApplicationContext(), "解冻应用: " + input, Toast.LENGTH_SHORT).show();
                                    String disable = "pm enable " + input;
                                    ShellUtils.execCommand(disable, true);
                                } catch (NumberFormatException e) {
                                    Toast.makeText(LiteActivity.this, "出错了", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LiteActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .show();
        }
        if (preference.getKey().equals("player")) {
            if ((!new File("/data/system/Simplicity/data/player").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.player", "echo true >/data/system/Simplicity/data/player"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.player", "rm -rf /data/system/Simplicity/data/player"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("notes")) {

            if ((!new File("/data/system/Simplicity/data/notes").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.notes", "echo true >/data/system/Simplicity/data/notes"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.notes", "rm -rf /data/system/Simplicity/data/notes"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("email")) {

            if ((!new File("/data/system/Simplicity/data/email").exists())) {
                String[] cmd = new String[]{"pm disable com.android.email", "echo true >/data/system/Simplicity/data/email"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.android.email", "rm -rf /data/system/Simplicity/data/email"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("calculator")) {

            if ((!new File("/data/system/Simplicity/data/calculator").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.calculator", "echo true >/data/system/Simplicity/data/calculator"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.calculator", "rm -rf /data/system/Simplicity/data/calculator"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("virtualsim")) {

            if ((!new File("/data/system/Simplicity/data/virtualsim").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.virtualsim", "echo true >/data/system/Simplicity/data/virtualsim"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.virtualsim", "rm -rf /data/system/Simplicity/data/virtualsim"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("weather2")) {

            if ((!new File("/data/system/Simplicity/data/weather2").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.weather2", "echo true >/data/system/Simplicity/data/weather2"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.weather2", "rm -rf /data/system/Simplicity/data/weather2"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("remotecontroller")) {

            if ((!new File("/data/system/Simplicity/data/remotecontroller").exists())) {
                String[] cmd = new String[]{"pm disable com.duokan.phone.remotecontroller", "echo true >/data/system/Simplicity/data/remotecontroller"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.duokan.phone.remotecontroller", "rm -rf /data/system/Simplicity/data/remotecontroller"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("shop")) {

            if ((!new File("/data/system/Simplicity/data/shop").exists())) {
                String[] cmd = new String[]{"pm disable com.xiaomi.shop", "echo true >/data/system/Simplicity/data/shop"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.xiaomi.shop", "rm -rf /data/system/Simplicity/data/shop"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("compass")) {

            if ((!new File("/data/system/Simplicity/data/compass").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.compass", "echo true >/data/system/Simplicity/data/compass"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.compass", "rm -rf /data/system/Simplicity/data/compass"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("browser")) {

            if ((!new File("/data/system/Simplicity/data/browser").exists())) {
                String[] cmd = new String[]{"pm disable com.android.browser", "echo true >/data/system/Simplicity/data/browser"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.android.browser", "rm -rf /data/system/Simplicity/data/browser"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("soundrecorder")) {

            if ((!new File("/data/system/Simplicity/data/soundrecorder").exists())) {
                String[] cmd = new String[]{"pm disable com.android.soundrecorder", "echo true >/data/system/Simplicity/data/soundrecorder"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.android.soundrecorder", "rm -rf /data/system/Simplicity/data/soundrecorder"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("calendar")) {

            if ((!new File("/data/system/Simplicity/data/calendar").exists())) {
                String[] cmd = new String[]{"pm disable com.android.calendar", "echo true >/data/system/Simplicity/data/calendar"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.android.calendar", "rm -rf /data/system/Simplicity/data/calendar"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("updater")) {

            if ((!new File("/data/system/Simplicity/data/updater").exists())) {
                String[] cmd = new String[]{"pm disable com.android.updater", "echo true >/data/system/Simplicity/data/updater"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.android.updater", "rm -rf /data/system/Simplicity/data/updater"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("bugreport")) {

            if ((!new File("/data/system/Simplicity/data/bugreport").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.bugreport", "echo true >/data/system/Simplicity/data/bugreport"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.bugreport", "rm -rf /data/system/Simplicity/data/bugreport"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("wallet")) {

            if ((!new File("/data/system/Simplicity/data/wallet").exists())) {
                String[] cmd = new String[]{"pm disable com.mipay.wallet", "echo true >/data/system/Simplicity/data/wallet"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.mipay.wallet", "rm -rf /data/system/Simplicity/data/wallet"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        if (preference.getKey().equals("video")) {

            if ((!new File("/data/system/Simplicity/data/video").exists())) {
                String[] cmd = new String[]{"pm disable com.miui.video", "echo true >/data/system/Simplicity/data/video"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            } else {
                String[] cmd = new String[]{"pm enable com.miui.video", "rm -rf /data/system/Simplicity/data/video"} ;
                ShellUtils.execCommand(cmd, true);
                setsummary();
            }
        }
        return false;
    }

    private void setsummary() {
        CheckBoxPreference notes = (CheckBoxPreference) findPreference("notes");
        CheckBoxPreference email = (CheckBoxPreference) findPreference("email");
        CheckBoxPreference calculator = (CheckBoxPreference) findPreference("calculator");
        CheckBoxPreference virtualsim = (CheckBoxPreference) findPreference("virtualsim");
        CheckBoxPreference weather2 = (CheckBoxPreference) findPreference("weather2");
        CheckBoxPreference remotecontroller = (CheckBoxPreference) findPreference("remotecontroller");
        CheckBoxPreference shop = (CheckBoxPreference) findPreference("shop");
        CheckBoxPreference compass = (CheckBoxPreference) findPreference("compass");
        CheckBoxPreference browser = (CheckBoxPreference) findPreference("browser");
        CheckBoxPreference soundrecorder = (CheckBoxPreference) findPreference("soundrecorder");
        CheckBoxPreference calendar = (CheckBoxPreference) findPreference("calendar");
        CheckBoxPreference updater = (CheckBoxPreference) findPreference("updater");
        CheckBoxPreference bugreport = (CheckBoxPreference) findPreference("bugreport");
        CheckBoxPreference wallet = (CheckBoxPreference) findPreference("wallet");
        CheckBoxPreference video = (CheckBoxPreference) findPreference("video");
        CheckBoxPreference player = (CheckBoxPreference) findPreference("player");

        findPreference("switch").setTitle("冻结开关");
        player.setTitle("音乐");
        updater.setTitle("系统更新");
        bugreport.setTitle("用户反馈");
        browser.setTitle("浏览器");
        soundrecorder.setTitle("录音机");
        shop.setTitle("小米商城");
        remotecontroller.setTitle("万能遥控");
        notes.setTitle("便签");
        email.setTitle("电子邮件");
        calculator.setTitle("计算器");
        calendar.setTitle("日历");
        compass.setTitle("指南针");
        video.setTitle("视频");
        virtualsim.setTitle("全球上网");
        wallet.setTitle("小米钱包");
        weather2.setTitle("天气");

        findPreference("diy").setTitle("自定义冻结");
        findPreference("cust").setTitle("输入包名");

        if ((new File("/data/system/Simplicity/data/notes").exists())) {
            notes.setChecked(true);
        } else {
            notes.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/email").exists())) {
            email.setChecked(true);
        } else {
            email.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/calculator").exists())) {
            calculator.setChecked(true);
        } else {
            calculator.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/calendar").exists())) {
            calendar.setChecked(true);
        } else {
            calendar.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/compass").exists())) {
            compass.setChecked(true);
        } else {
            compass.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/video").exists())) {
            video.setChecked(true);
        } else {
            video.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/virtualsim").exists())) {
            virtualsim.setChecked(true);
        } else {
            virtualsim.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/wallet").exists())) {
            wallet.setChecked(true);
        } else {
            wallet.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/weather2").exists())) {
            weather2.setChecked(true);
        } else {
            weather2.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/remotecontroller").exists())) {
            remotecontroller.setChecked(true);
        } else {
            remotecontroller.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/shop").exists())) {
            shop.setChecked(true);
        } else {
            shop.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/soundrecorder").exists())) {
            soundrecorder.setChecked(true);
        } else {
            soundrecorder.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/browser").exists())) {
            browser.setChecked(true);
        } else {
            browser.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/bugreport").exists())) {
            bugreport.setChecked(true);
        } else {
            bugreport.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/updater").exists())) {
            updater.setChecked(true);
        } else {
            updater.setChecked(false);
        }
        if ((new File("/data/system/Simplicity/data/player").exists())) {
            player.setChecked(true);
        } else {
            player.setChecked(false);
        }
    }

    private void CheckRoot() {
        if ((!new File("/tmp/rooted").exists())) {
            String[] commands = new String[]{"mount -o rw,remount /", "mount -o rw,remount /system", "mount -o rw,remount /vendor", "mount -o rw,remount /vendor/etc", "mount -o rw,remount /system/vendor/etc", "mount -o rw,remount /system/system", "mount -o rw,remount /system/etc", "mount -o rw,remount /system_root/system", "echo test >/system/test", "echo test >/test", "chmod -R 0777 /system/test", "chmod -R 0777 /test", "sync"};
            ShellUtils.execCommand(commands, true);
            if ((!new File("/system/test").exists()) || (!new File("/test").exists())) {
                AlertDialog.Builder dialog = new AlertDialog.
                        Builder(LiteActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("无法获取系统读写权限，请ROOT你的设备！");
                dialog.setCancelable(false);
                dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                dialog.show();
            } else {
                String[] command = new String[]{"mount -o rw,remount /", "mount -o rw,remount /system", "mount -o rw,remount /vendor", "mount -o rw,remount /vendor/etc", "mount -o rw,remount /system/vendor/etc", "mount -o rw,remount /system/system", "mount -o rw,remount /system/etc", "mount -o rw,remount /system_root/system", "mkdir /tmp", "chmod -R 777 /tmp", "echo 1 >/tmp/rooted", "chmod -R 0777 /tmp/rooted", "rm -rf /system/test", "rm -rf /test", "sync"};
                ShellUtils.execCommand(command, true);
            }
        }
    }
    private void mount() {
        String[] command = new String[]{"mount -o rw,remount /", "mount -o rw,remount /system", "mount -o rw,remount /vendor", "mount -o rw,remount /vendor/etc", "mount -o rw,remount /system/vendor/etc", "mount -o rw,remount /system/system", "mount -o rw,remount /system/etc", "mount -o rw,remount /system_root/system"};
        ShellUtils.execCommand(command, true);
        if ((!new File("/system/bin/chmod777").exists())) {
            String[] commands = new String[]{"mount -o rw,remount /", "mount -o rw,remount /system", "mount -o rw,remount /vendor", "mount -o rw,remount /vendor/etc", "mount -o rw,remount /system/vendor/etc", "mount -o rw,remount /system/system", "mount -o rw,remount /system/etc", "mount -o rw,remount /system_root/system", "mkdir /tmp", "chmod -R 0777 /tmp", "chmod -R 777 /system/res", "chmod -R 777 /system/tools", "echo chmod777 >/system/bin/chmod777", "sync"};
            ShellUtils.execCommand(commands, true);
        }
    }
}