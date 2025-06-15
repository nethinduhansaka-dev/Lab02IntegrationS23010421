# VPN Hansaka [s23010421] - EEI4369 Mobile Application Development Lab Test

## 📱 Overview

This is a comprehensive Android application developed as part of the EEI4369 Mobile Application Development lab test. The app demonstrates integration of multiple Android components including multimedia, Google Maps, sensor monitoring, and SQLite database management within a single cohesive application.

## ✨ Features

### 🚀 Get Started Screen
- Professional welcome interface with app feature overview
- Clean navigation to login system

### 🔐 Login System with Database Integration
- Student ID, Username, and Password authentication
- SQLite database storage for user credentials
- Toast confirmations for successful data operations
- Multimedia background elements (video/audio integration)

### 🗺️ Google Maps Integration
- Interactive map display with geocoding functionality
- Address search with "Show Location" feature
- Real-time location marking on map
- Seamless navigation between app components

### 🌡️ Temperature Sensor Monitoring
- Real-time temperature sensor integration
- Customizable threshold based on Student ID (last two digits)
- Automatic audio alert system when threshold exceeded
- Offline audio playback functionality
- Simulation mode for testing on devices without sensors

### 💾 SQLite Database Management
- Local data storage for user information
- CRUD operations with proper error handling
- Database schema: StudentID, Username, Password
- Toast message confirmations for all database operations

## 🛠️ Technologies Used

- **Programming Language:** Java
- **IDE:** Android Studio
- **Database:** SQLite
- **Maps:** Google Maps SDK for Android
- **Sensors:** Android Sensor Framework
- **UI:** ConstraintLayout, Material Design Components
- **Multimedia:** MediaPlayer, VideoView
- **Minimum SDK:** API 24 (Android 7.0)
- **Target SDK:** API 35

## 📋 Prerequisites

- Android Studio Arctic Fox or later
- Android SDK API 24+
- Google Play Services
- Physical Android device or emulator
- Google Maps API Key

## 🚀 Installation & Setup

### 1. Clone the Repository
 - git clone https://github.com/nethinduhansaka-dev/Lab02IntegrationS23010421.git
 - cd vpnhansaka-lab-test


### 2. Open in Android Studio
- Open Android Studio
- Select "Open an existing Android Studio project"
- Navigate to the cloned repository folder
- Click "OK"

### 3. Configure Google Maps API
1. Get your Google Maps API key from [Google Cloud Console](https://console.cloud.google.com/)
2. Enable "Maps SDK for Android" in your Google Cloud project
3. Replace `YOUR_MAPS_API_KEY` in `AndroidManifest.xml`:


### 4. Add Multimedia Resources
- Place video files in `res/raw/` directory:
  - `sample_video.mp4` - Login screen background video
  - `background_audio.mp3` - Login screen background audio
  - `alert_sound.mp3` - Temperature threshold alert sound

### 5. Build and Run
- Connect your Android device or start an emulator
- Click "Run" button in Android Studio
- Select your target device

## 📖 Usage Guide

### Navigation Flow
1. **Get Started Screen** → Tap "Get Started" button
2. **Login Screen** → Enter credentials and tap "Login"
3. **Maps Screen** → Search locations and tap "Next"
4. **Sensor Screen** → Monitor temperature with automatic alerts

### Testing Temperature Sensor
- **Physical Device:** Use in different temperature environments
- **Emulator:** Use Virtual Sensors in emulator settings
- **Simulation:** Use "Simulate Temperature" button for testing

### Database Operations
- All login attempts automatically save data to SQLite database
- Toast messages confirm successful operations
- View database using Android Studio's Database Inspector

## 📁 Project Structure

```
app/src/main/
├── java/com/s23010421/vpnhansaka/
│   ├── MainActivity.java          # Get Started Screen
│   ├── LoginActivity.java         # Login Form with Database
│   ├── MapsActivity.java          # Google Maps Integration
│   ├── SensorActivity.java        # Temperature Sensor Monitor
│   └── DatabaseHelper.java        # SQLite Database Handler
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # Get Started UI
│   │   ├── activity_login.xml     # Login Form UI
│   │   ├── activity_maps.xml      # Maps Interface
│   │   └── activity_sensor.xml    # Sensor Monitor UI
│   ├── raw/                       # Multimedia files
│   └── values/                    # Strings, colors, styles
└── AndroidManifest.xml            # App configuration
```


## 📊 Database Schema

**Table:** `students_table`

| Column | Type | Description |
|--------|------|-------------|
| ID | INTEGER | Primary Key (Auto-increment) |
| STUDENT_ID | TEXT | Student identification number |
| USERNAME | TEXT | User's chosen username |
| PASSWORD | TEXT | User's password |



### Device Compatibility
- Tested on Android API 24+ devices
- Supports both emulator and physical device testing
- Temperature sensor functionality varies by device hardware



## 🤝 Contributing

This project is developed for academic evaluation. For suggestions or improvements:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/improvement`)
3. Commit your changes (`git commit -m 'Add improvement'`)
4. Push to the branch (`git push origin feature/improvement`)
5. Open a Pull Request

## 📄 License

This project is developed for educational purposes as part of EEI4369 lab test requirements.

## 📞 Contact

- **Developer:**V.P.N.Hansaka
- **Email:** S23010421@ousl.lk
- **Student ID:** S23010421
- **GitHub:** [https://github.com/nethinduhansaka](https://github.com/nethinduhansaka-dev)

## 🙏 Acknowledgments

- EEI4369 Course Instructors and Lab Supervisors
- The Open University of Sri Lanka
- Google Maps Platform Documentation
- Android Developer Documentation
- Open source community contributions

---

**⭐ If you found this project helpful, please give it a star!**

*Last Updated: June 2025*
