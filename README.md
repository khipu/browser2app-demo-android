# browser2app-demo-android

Version 2.0

Esta aplicación ha sido creada para demostrar la utilización de nuestra biblioteca khenshin.
Para poder ejecutar esta aplicación es necesario que tengas acceso a nuestro repositorio privado: https://dev.khipu.com/nexus/content/repositories/browser2app

## Repositorios

Se debe incluir el [repositorio maven de khenshin](https://dev.khipu.com/nexus/content/repositories/browser2app) así como jcenter y el repositorio del proyecto [dsl4xml](https://github.com/steveliles/dsl4xml)


    allprojects {
		repositories {
			jcenter()
			maven {
				url 'http://steveliles.github.com/repository/'
			}
			maven {
				url 'https://dev.khipu.com/nexus/content/repositories/browser2app'
				credentials {
					username khenshinRepoUsername
					password khenshinRepoPassword
				}
			}
		}
	}
	
Los campos khenshinRepoUsername y khenshinRepoPassword te serán proporcionados por tu ejecutivo Khenshin, se deben incluir en el archivo gradle.properties en la raiz del proyecto y sin incluir al sistema de control de versiones.

## Dependencias

Con los repositorios agregados puedes agregar el paquete khenshin a tu proyecto.

    compile 'com.browser2app:khenshin:1.5.8'
    
## Clase de tu aplicación

La clase principal de tu aplicación (la definida en el atributo android:name dentro del tag application en el AndroidManifest.xml) debe implementar la interfaz KhenshinApplication y en el constructor debe inicializar a Khenshin

	public class Demo extends Application implements KhenshinApplication {
	
		...
		
		private KhenshinInterface khenshin;
	
		@Override
		public KhenshinInterface getKhenshin() {
			return khenshin;
		}
	
		...
	
		public Demo() {
			super();
			khenshin = new Khenshin.KhenshinBuilder()
					.setApplication(this)
					.setAutomatonAPIUrl(AUTOMATA_API_URL)
					.setCerebroAPIUrl(CEREBRO_API_URL)
					.setMainButtonStyle(Khenshin.CONTINUE_BUTTON_IN_FORM)
					.setAllowCredentialsSaving(false)
					.setHideWebAddressInformationInForm(false)
					.build();
		}
		
		...
	
	}
	
	
Los parámetros AUTOMATA_API_URL y CEREBRO_API_URL te serán proporcionados por tu ejecutivo Khenshin.

El parámetro MainButtonStyle puede tomar los valores Khenshin.CONTINUE_BUTTON_IN_FORM (El botón principal se pinta en bajo el formulario) o Khenshin.CONTINUE_BUTTON_IN_TOOLBAR (El botón principal se pinta en la barra de navegación).

Con AllowCredentialsSaving se enciende o apaga la opción de recordar credenciales en el dispositivo.

Con HideWebAddressInformationInForm se esconde la URL de navegación.


## Colores

En tu proyecto puedes determinar los colores que usará Khenshin en las pantallas de pago sobreescribiendo los siguiente parámetros en los recursos de tu proyecto (por ejemplo en un archivo colors.xml dentro de res/values)

    <color name="khenshin_primary">#ca0814</color> <!-- Color de la barra de navegación y botón principal-->
    <color name="khenshin_primary_dark">#580409</color> <!-- Color del status bar superior -->
    <color name="khenshin_primary_text">#ffffff</color> <!-- Color del texto en la barra de navegación -->
    <color name="khenshin_accent">#ca0814</color> <!-- Color de las decoraciones, por ejemplo barras de progreso -->
    
## Vistas

Para personalizar más aún la visualización de Khenshin puedes sobreescribir archivos de layout que se utilizan en el proceso de pago:


### khenshin_toolbar_title.xml

Este layout se usa en la barra de navegación en las páginas de salida (exito, fracaso o advertencia) y en las páginas del proceso si la barra del navegador está oculta.

	<?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        style="@style/khenshin_toolbar_title">
        <TextView
            style="@style/khenshin_toolbar_title_text"
            android:text="@string/app_name"
        />
    </LinearLayout>

### khenshin_process_header.xml

Este layout se utiliza en todas las páginas intermedias del proceso de pago. Khenshin viene con la siguiente implementación:

	<?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/khenshin_horizontal_wrapper">
        <ImageView
                style="@style/khenshin_large_image"
                android:id="@+id/merchantImage"/>
        <LinearLayout
                style="@style/khenshin_column_70"
                android:orientation="vertical"
                android:layout_height="wrap_content"
                android:gravity="center|left">
            <TextView
                    android:id="@+id/merchantName"
                    style="@style/khenshin_pay_title"
                    android:layout_marginTop="10dp"/>
    
            <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:text="@string/paymentSubject"
                        android:layout_height="wrap_content"/>
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:gravity="right"
                        android:layout_height="wrap_content"
                        android:id="@+id/subject"/>
            </LinearLayout>
    
            <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:text="@string/paymentAmount"
                        android:layout_height="wrap_content"/>
    
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:gravity="right"
                        android:layout_height="wrap_content"
                        android:id="@+id/amount"/>
    
            </LinearLayout>
            <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal">
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:text="@string/paymentMethod"
                        android:layout_height="wrap_content"/>
                <TextView
                        style="@style/khenshin_dialog_title"
                        android:layout_width="0dp"
                        android:layout_weight=".5"
                        android:gravity="right"
                        android:layout_height="wrap_content"
                        android:id="@+id/paymentMethod"/>
            </LinearLayout>
        </LinearLayout>
    </LinearLayout>
    
    
Khenshin reemplazará los valores de los siguientes campos (Tipo y android:id)

- ImageView: android:id="@+id/merchantImage"
- TextView: android:id="@+id/merchantName"
- TextView: android:id="@+id/subject"
- TextView: android:id="@+id/amount"
- TextView: android:id="@+id/paymentMethod"
    

### khenshin_process_success.xml

Se utiliza al finalizar el proceso de manera exitosa. La implementación por defecto es:

	<?xml version="1.0" encoding="utf-8"?>
    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
                style="@style/khenshin_page_body">
    
    
        <LinearLayout style="@style/khenshin_vertical_wrapper_padded">
    
            <include layout="@layout/khenshin_finish_header"/>
    
            <View style="@style/khenshin_horizontal_separator"/>
    
            <TextView
                    android:id="@+id/title"
                    style="@style/khenshin_dialog_title"/>
    
    
            <ImageView
                android:id="@+id/exitImage"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:layout_gravity="center_horizontal"
                android:src="@drawable/ic_transfer_ok"
                android:paddingBottom="10dp"/>
    
            <TextView
                android:id="@+id/message"
                style="@style/khenshin_dialog_message"
                />
    
            <Button android:id="@+id/nextButton" android:visibility="gone" android:layout_width="match_parent" android:layout_height="wrap_content"
                    android:text="@string/khenshinFinish" style="@style/khenshin_button"/>
    
    
        </LinearLayout>
    
    
    </ScrollView>
    
Khenshin reemplazará los valores de los siguientes campos (Tipo y android:id)

- TextView: android:id="@+id/title"
- TextView: android:id="@+id/message"

### khenshin_process_warning.xml

	<?xml version="1.0" encoding="utf-8"?>
    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
                style="@style/khenshin_page_body">
    
    
        <LinearLayout style="@style/khenshin_vertical_wrapper_padded">
    
            <include layout="@layout/khenshin_finish_header"/>
    
            <View style="@style/khenshin_horizontal_separator"/>
    
            <TextView
                    android:id="@+id/title"
                    style="@style/khenshin_dialog_title"/>
    
    
            <ImageView
                    android:id="@+id/exitImage"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:layout_gravity="center_horizontal"
                    android:src="@drawable/ic_transfer_warning"
                    android:paddingBottom="10dp"/>
    
            <TextView
                    android:id="@+id/message"
                    style="@style/khenshin_dialog_message"
            />
    
            <Button android:id="@+id/nextButton" android:visibility="gone" android:layout_width="match_parent" android:layout_height="wrap_content"
                    android:text="@string/khenshinFinish" style="@style/khenshin_button"/>
    
    
        </LinearLayout>
    
    
    </ScrollView>

Khenshin reemplazará los valores de los siguientes campos (Tipo y android:id)

- TextView: android:id="@+id/title"
- TextView: android:id="@+id/message"

### khenshin_process_failure.xml

	<?xml version="1.0" encoding="utf-8"?>
    <ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
                style="@style/khenshin_page_body">
    
    
        <LinearLayout style="@style/khenshin_vertical_wrapper_padded">
    
            <TextView
                android:id="@+id/title"
                style="@style/khenshin_dialog_title"/>
    
    
            <ImageView
                android:id="@+id/exitImage"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:layout_gravity="center_horizontal"
                android:src="@drawable/ic_transfer_error"
                android:paddingBottom="10dp"/>
    
            <TextView
                android:id="@+id/message"
                style="@style/khenshin_dialog_message"
                />
    
            <Button android:id="@+id/nextButton" android:visibility="gone" android:layout_width="match_parent" android:layout_height="wrap_content"
                    android:text="@string/khenshinFinish" style="@style/khenshin_button"/>
    
    
        </LinearLayout>
    
    
    </ScrollView>
    
Khenshin reemplazará los valores de los siguientes campos (Tipo y android:id)

- TextView: android:id="@+id/title"
- TextView: android:id="@+id/message"    
    
## Invocación

Khenshin se puede invocar de dos maneras. La primera es a partir de un pago generado con [la API de creación de pagos](https://khipu.com/page/api) de khipu y la segunda es a partir de un identificador de autómata y un set de parámetros.


### Invocación a partir de un pago generado en khipu.com

Para iniciar un pago con Khenshin debes iniciar la actividad StartPaymentActivity.

	int START_PAYMENT_REQUEST_CODE = 101;
	
	...
	
	Intent intent = new Intent(MainActivity.this, StartPaymentActivity.class);
	intent.putExtra(KhenshinConstants.EXTRA_PAYMENT_ID, paymentId.getText().toString());  // ID DEL PAGO
	intent.putExtra(KhenshinConstants.EXTRA_FORCE_UPDATE_PAYMENT, false); // NO FORZAR LA ACTUALIZACION DE DATOS
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // LIMPIAR EL STACK DE ACTIVIDADES
	startActivityForResult(intent, START_PAYMENT_REQUEST_CODE); // INICIAR LA ACTIVIDAD ESPERANDO UNA RESPUESTA
		
El Id de pago se debe obtener luego de crear un pago [ver API de creación de pagos](https://khipu.com/page/api)


### Invocación a partir de un identificador de autómata y parámetros del proceso.

	Intent intent = new Intent(MainActivity.this, StartPaymentActivity.class);
	intent.putExtra(KhenshinConstants.EXTRA_AUTOMATON_ID, ID_DE_AUTOMATA);
	Bundle params = new Bundle();
	
	params.putString(PARAM_1, VALOR_PARAM_1);
	params.putString(PARAM_2, VALOR_PARAM_2);
	params.putString(PARAM_3, VALOR_PARAM_3);
	...
	params.putString(PARAM_N, VALOR_PARAM_N);

	intent.putExtra(KhenshinConstants.EXTRA_AUTOMATON_PARAMETERS, params);
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	startActivityForResult(intent, START_PAYMENT_REQUEST_CODE);

Tu ejecutivo Khenshin te informará las opciones para el ID_DE_AUTOMATA y los PARAM_1 a PARAM_N

## Respuesta

En la actividad de tu aplicación que inició la actividad de pago se debe implementar el método onActivityResult

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == START_PAYMENT_REQUEST_CODE) {
			String exitUrl = data.getStringExtra(KhenshinConstants.EXTRA_INTENT_URL);
			if (resultCode == RESULT_OK) {
				Toast.makeText(MainActivity.this, "PAYMENT OK, exit url: " + exitUrl,
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this, "PAYMENT FAILED, exit url: " + exitUrl,
						Toast.LENGTH_LONG).show();
			}
		}
	
	}
	
El parámetro requestCode debe ser el mismo que se envió al iniciar la actividad.

El parámetro resultCode será RESULT_OK si el pago terminó exitósamente o RESULT_CANCEL si el usuario no completó el pago.

En data vendrá un extra de nombre KhenshinConstants.EXTRA_INTENT_URL que tendrá la URL de salida definida al momento de crear el pago.

    
