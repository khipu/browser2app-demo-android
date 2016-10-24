# browser2app-demo-android

Version 1.0

Esta aplicación ha sido creada para demostrar la utilización de nuestra biblioteca khenshin.
Para poder ejecutar esta aplicación es necesario que tengas acceso a nuestro repositorio privado: https://dev.khipu.com/nexus/content/repositories/releases

## Repositorios

Se debe incluir el [repositorio maven de khenshin](https://dev.khipu.com/nexus/content/repositories/releases) así como jcenter y el repositorio del proyecto [dsl4xml](https://github.com/steveliles/dsl4xml)


    allprojects {
		repositories {
			jcenter()
			maven {
				url 'http://steveliles.github.com/repository/'
			}
			maven {
				url 'https://dev.khipu.com/nexus/content/repositories/releases'
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

    compile 'com.browser2app:khenshin:1.1.1'
    
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
					.setAutomatonApiUrl("https://khipu.com/app/2.0/")
					.setCerebroApiUrl("https://khipu.com/cerebro/")
					.build();
		}
		
		...
	
	}
	
	
Los parámetros AutomatonApiUrl y CerebroApiUrl te serán proporcionados por tu ejecutivo Khenshin.

## Look & Feel

En tu proyecto puedes determinar los colores que usará Khenshin en las pantallas de pago sobreescribiendo los siguiente parámetros en los recursos de tu proyecto (por ejemplo en un archivo colors.xml dentro de res/values)

    <color name="khenshin_primary">#ca0814</color> <!-- Color de la barra de navegación -->
    <color name="khenshin_primary_dark">#580409</color> <!-- Color del status bar superior -->
    <color name="khenshin_primary_text">#ffffff</color> <!-- Color del texto en la barra de navegación -->
    <color name="khenshin_accent">#ca0814</color> <!-- Color de las decoraciones, por ejemplo barras de progreso -->
    
## Invocación

Para iniciar un pago con Khenshin debes iniciar la actividad StartPaymentActivity.

	int START_PAYMENT_REQUEST_CODE = 101;
	
	...
	
	Intent intent = new Intent(MainActivity.this, StartPaymentActivity.class);
	intent.putExtra(KhenshinConstants.EXTRA_PAYMENT_ID, paymentId.getText().toString());  // ID DEL PAGO
	intent.putExtra(KhenshinConstants.EXTRA_FORCE_UPDATE_PAYMENT, false); // NO FORZAR LA ACTUALIZACION DE DATOS
	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // LIMPIAR EL STACK DE ACTIVIDADES
	startActivityForResult(intent, START_PAYMENT_REQUEST_CODE); // INICIAR LA ACTIVIDAD ESPERANDO UNA RESPUESTA
		
El Id de pago se debe obtener luego de crear un pago [ver API de creación de pagos](https://khipu.com/page/api)

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

    
