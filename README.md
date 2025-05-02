# 🇮🇳 LangLearnDemo

LangLearnDemo is a phonetic emergency sentence learning app designed to help users overcome language barriers in India by teaching them how to pronounce essential phrases correctly in regional languages.

---

## 🧭 Purpose

India has 22 official languages and hundreds of regional dialects.  
Travelers, migrants, and citizens in unfamiliar states may face life-threatening situations if they cannot communicate basic needs or distress.

This app addresses that by helping users speak and pronounce critical emergency sentences clearly — even if they don't understand the script.

---

## 📱 Demo Version Highlights

- 🔤 Select your known language: English
- 🗣️ Learn how to speak Hindi, Marathi, or Kannada emergency phrases
- 🧩 Sentences are split into word-by-word lessons for phonetic clarity
- 📶 Works offline — lightweight and accessible
- 🎯 Designed to integrate AI feedback and emergency detection in future versions

> ⚠️ Note: This demo includes only 2 target languages to protect against replication.  
> The full app currently supports 10 Indian languages, with plans to scale to all 22.

---

## 🖼️ Screenshots

## Demo App – Screenshot Walkthrough

### 1. Home Page

Default Homepage View  
![Home Page](assets/screenshots/demo-app/home_page/home_page_en.jpg)

---

### 2. Language Selection & Terms

Choose Your Known Language  
![Language Selection](assets/screenshots/demo-app/language_selection_terms/language_select.jpg)

Terms & Conditions (Hindi Example)  
![Terms](assets/screenshots/demo-app/language_selection_terms/Terms_Condtions_hi.jpg)

---

### 3. Sentence List

Which Language to Learn  
![Which Language](assets/screenshots/demo-app/language_sentences/which_lang_to_learn_en.jpg)

Sentence List (English Interface)  
![Sentence List](assets/screenshots/demo-app/language_sentences/sentences_en.jpg)

---

### 4. Login Flow

Login Page (English)  
![Login Page](assets/screenshots/demo-app/login_otp_username/login_page_en.jpg)

---

### 5. Phonetic Learning

Word-by-Word Pronunciation (Marathi from English)  
![Phonetic Learning](assets/screenshots/demo-app/phonetic_learning/learning_marathi_from_english.jpg)

---

👉 *To view all demo screenshots, visit the [`demo-app/`](assets/screenshots/demo-app/) folder.*




## 🎙️ Recording App – Screenshot Walkthrough

This companion app is used to collect native speaker recordings securely and efficiently.  
The APK is private for safety and privacy reasons, but here’s the full UI workflow:

---

### 1. Login & Language Setup

### 1. Login & Language Setup

**Login with Email**  
![Login](assets/screenshots/recording-app/01_login.webp)

**Choose Language**  
![Choose Language](assets/screenshots/recording-app/03_choose_language.webp)

**Choose Gender**  
![Choose Gender](assets/screenshots/recording-app/06_choose_gender.webp)

---

### 2. Sentence Display

**Sentence Display Before Recording**  
![Sentence](assets/screenshots/recording-app/02_sentences.webp)

---

### 3. Recording Process

**Microphone Permission Prompt**  
![Mic Permission](assets/screenshots/recording-app/04_record_permission.webp)

**Recording Popup**  
![Recording](assets/screenshots/recording-app/05_record.webp)

---

### 4. Upload to Firebase

**Send to Database**  
![Send to DB](assets/screenshots/recording-app/07_send_to_db.webp)


---

> ⚠️ *The recording app is not open-sourced due to privacy and security of voice data. These screenshots illustrate the complete process for reviewers and collaborators.*


---

## 🔍 Project Structure

---

## 🧠 AI Model (Training in Progress)

Future versions will include:

### 🎙️ Pronunciation Scoring
- Compares user recordings with native phoneme maps
- Uses MFCC, TensorFlow, librosa, and optional Wav2Vec2
- Scores pronunciation accuracy (target: ≥75%)

### 🚨 Emergency Detection Mode
- Real-time voice input
- NLP model flags distress or harmful phrases in 10+ Indian languages
- Alerts user if potential verbal threats are detected

For full pipeline details → [ai-model/README.md](./ai-model/README.md)


🚧 The model is **in training**, and early results are being tested on verified user inputs.
---

## 🔐 Privacy & Security

- Voice data is collected via a private recording app from trusted contributors
- No identifying metadata is stored
- AI model and dataset are not open-sourced yet to prevent misuse
- Demo excludes full language set and training logic intentionally

---

## 📅 Roadmap

- [x] Sentence breakdown UI
- [x] Multi-language JSON structure
- [x] Firebase-secured recording pipeline
- [ ] Train and validate AI pronunciation model
- [ ] Emergency phrase detection integration
- [ ] Offline-first version with TTS/STT feedback

---

## 🤝 Collaborate With Me

If you're a native speaker of any underrepresented Indian language and want to contribute voice samples securely, please reach out.

📧 Email: [yadav.akanksha03@gmail.com]

---

## 📜 License

This project is licensed under the MIT License.  
You may use or modify this project with attribution, but redistribution of proprietary datasets or trained models is prohibited.


