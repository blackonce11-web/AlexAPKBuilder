package com.alex.apkbuilder;
import android.app.*; import android.os.*; import android.content.*; import android.graphics.Color; import android.view.*; import android.widget.*;
public class MainActivity extends Activity {
 EditText path; TextView status; LinearLayout root;
 int dp(int x){return (int)(x*getResources().getDisplayMetrics().density);}
 TextView txt(String s,int n){TextView t=new TextView(this);t.setText(s);t.setTextSize(n);t.setTextColor(Color.rgb(35,48,65));return t;}
 void gap(){View v=new View(this);root.addView(v,new LinearLayout.LayoutParams(1,dp(12)));}
 void btn(String s,Runnable r){Button b=new Button(this);b.setText(s);b.setOnClickListener(v->r.run());root.addView(b);}
 void card(String h,String d){LinearLayout c=new LinearLayout(this);c.setOrientation(1);c.setPadding(dp(14),dp(12),dp(14),dp(12));c.setBackgroundColor(Color.WHITE);TextView a=txt(h,17);a.setTypeface(null,1);c.addView(a);TextView b=txt(d,13);b.setPadding(0,dp(6),0,0);c.addView(b);LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2);p.bottomMargin=dp(12);root.addView(c,p);}
 public void onCreate(Bundle b){super.onCreate(b);ScrollView s=new ScrollView(this);root=new LinearLayout(this);root.setOrientation(1);root.setPadding(dp(18),dp(26),dp(18),dp(24));root.setBackgroundColor(Color.rgb(245,247,250));s.addView(root);setContentView(s);
 TextView title=txt("Alex APK Builder",28);title.setTypeface(null,1);root.addView(title);root.addView(txt("Compilador Android local • Sin cuotas por minuto",14));gap();
 card("Motor local","Esta versión usa Termux como motor de compilación. Tu código permanece en el teléfono; no se envía a servidores.");
 btn("Abrir Termux",()->openTermux());gap();root.addView(txt("Ruta absoluta del proyecto en Termux",17));
 path=new EditText(this);path.setSingleLine(true);path.setHint("/data/data/com.termux/files/home/MiProyecto");root.addView(path);
 root.addView(txt("La carpeta debe contener settings.gradle o settings.gradle.kts y el módulo app.",12));gap();
 btn("Compilar APK de prueba (Debug)",()->runBuild("assembleDebug"));
 btn("Compilar APK de lanzamiento (Release)",()->runBuild("assembleRelease"));
 btn("Limpiar compilación",()->runBuild("clean"));gap();
 card("Icono y datos de la app","El icono, nombre, package ID y versión se configuran en los archivos del proyecto Android. El editor visual de iconos y el importador ZIP están previstos para la siguiente versión; aún no están implementados.");
 card("Firma y seguridad","Debug usa una clave de depuración. Release requiere que el proyecto tenga su propia configuración de firma. Nunca introduzcas ni compartas contraseñas de keystore en esta app.");
 status=txt("Estado: listo. Configura Termux siguiendo README_ES.md.",14);root.addView(status);
 }
 void openTermux(){try{Intent i=getPackageManager().getLaunchIntentForPackage("com.termux");if(i==null)throw new Exception();startActivity(i);}catch(Exception e){status.setText("Termux no está instalado. Instálalo desde F-Droid o GitHub oficial.");}}
 void runBuild(String task){String p=path.getText().toString().trim();if(p.isEmpty()){path.setError("Introduce la ruta del proyecto");return;}if(p.contains("'")||p.contains(";")||p.contains("\n")){path.setError("Ruta no válida");return;}
 try{Intent i=new Intent("com.termux.RUN_COMMAND");i.setClassName("com.termux","com.termux.app.RunCommandService");i.putExtra("com.termux.RUN_COMMAND_PATH","/data/data/com.termux/files/usr/bin/bash");i.putExtra("com.termux.RUN_COMMAND_ARGUMENTS",new String[]{"-lc","cd '"+p+"' && gradle "+task+" --no-daemon"});i.putExtra("com.termux.RUN_COMMAND_WORKDIR",p);i.putExtra("com.termux.RUN_COMMAND_BACKGROUND",true);startService(i);status.setText("Tarea enviada a Termux: "+task+". Consulta la terminal para ver el progreso.");}catch(Exception e){status.setText("No se pudo iniciar Termux. Habilita allow-external-apps en termux.properties y reinicia Termux.");}}
}
