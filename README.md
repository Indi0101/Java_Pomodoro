# 🍅 Java Pomodoro

Aplicación de escritorio desarrollada en **Java y JavaFX** para administrar sesiones de concentración utilizando la técnica Pomodoro.

El proyecto permite configurar los tiempos de concentración y descanso, ejecutar el ciclo automáticamente y mantener un historial local de las sesiones realizadas.

Fue desarrollado como proyecto de aprendizaje para aplicar programación en Java a una aplicación de escritorio funcional, trabajando con interfaces gráficas, eventos, temporizadores, archivos y persistencia local.

---

## 🧠 Aspectos técnicos destacados

Este proyecto integra diferentes funcionalidades de Java dentro de una aplicación de escritorio completa:

- Desarrollo de interfaz gráfica con **JavaFX**.
- Manejo del estado del temporizador entre concentración y descanso.
- Ejecución del contador mediante `Timeline` y `KeyFrame`.
- Manejo de eventos de usuario.
- Validación y formateo de campos de tiempo.
- Reproducción de alertas mediante `MediaPlayer`.
- Notificaciones del sistema utilizando `SystemTray`.
- Registro de fecha y hora mediante Java Time API.
- Persistencia local de sesiones en archivos CSV.
- Lectura automática del historial al iniciar la aplicación.
- Visualización de registros mediante `TableView`.
- Uso de `ObservableList` para gestionar los datos mostrados en la interfaz.

---

## ✨ Funcionalidades

### ⏱️ Temporizador Pomodoro

La aplicación incluye un temporizador dividido en dos etapas:

- Tiempo de concentración
- Tiempo de descanso

Los valores predeterminados son:

- **25 minutos de concentración**
- **5 minutos de descanso**

Al finalizar el período de concentración, la aplicación cambia automáticamente al período de descanso.

---

### ⚙️ Configuración personalizada del tiempo

El usuario puede modificar manualmente:

- Horas
- Minutos
- Segundos

tanto para la sesión de concentración como para la sesión de descanso.

Esto permite utilizar la aplicación con intervalos diferentes al Pomodoro tradicional.

---

### ▶️ Controles del temporizador

La interfaz dispone de controles para:

- Iniciar el Pomodoro
- Detener el temporizador
- Reiniciar los tiempos
- Cambiar la duración de concentración y descanso
- Consultar el historial de sesiones

Los controles utilizan iconos SVG y muestran información mediante tooltips.

---

## 🔄 Ciclo automático

El temporizador utiliza `Timeline` de JavaFX para actualizar el tiempo cada segundo.

Cuando el tiempo de concentración llega a cero:

1. Se reproduce una alerta sonora.
2. La aplicación cambia automáticamente al modo de descanso.
3. Se muestra una notificación del sistema.
4. Comienza la cuenta regresiva del descanso.

Al finalizar el ciclo se reproduce una nueva alerta y el temporizador se detiene.

---

## 🔔 Alertas y notificaciones

La aplicación incorpora dos formas de notificar al usuario:

### Alertas de audio

Utiliza `Media` y `MediaPlayer` de JavaFX para reproducir sonidos durante los cambios del ciclo.

### Notificaciones del sistema

Utiliza `SystemTray` y `TrayIcon` de Java AWT para mostrar notificaciones de escritorio cuando cambia el estado del Pomodoro.

---

## 📊 Historial de sesiones

Cada vez que se inicia una sesión se registra:

- Fecha y hora
- Tiempo de concentración
- Tiempo de descanso

La aplicación dispone de una ventana independiente para consultar estos registros mediante un `TableView` de JavaFX.

La tabla muestra:

| Fecha | Concentración | Descanso |
|---|---|---|
| Fecha y hora de la sesión | Tiempo configurado | Tiempo configurado |

---

## 💾 Persistencia de datos

El historial se almacena localmente en:

```text 
registros_pomodoro.csv
```

## 📌 Estado del proyecto

Proyecto completado y funcional desarrollado como parte de mi formación en Java.

La aplicación permite configurar sesiones de concentración y descanso, ejecutar automáticamente el ciclo Pomodoro, reproducir alertas, mostrar notificaciones y mantener un historial persistente de las sesiones realizadas.
