# 🧠 AI Model – Phonetic Accuracy & Emergency Detection (In Progress)

Building a custom pronunciation feedback system and multilingual emergency detection AI.

### Goals:
- Match user pronunciation with ideal timing, stress, and frequency.
- Support low-resource Indian languages using phoneme-based comparison.

### Current Work:
- ✅ **Data collection** from native speakers via closed-source recording app.
- 🛠️ **Training** in progress – inspired by Kaldi and DeepSpeech architectures.
- 🧪 Libraries: `TensorFlow`, `librosa`, `phonemizer`, optional `Wav2Vec2`.

---

## 🚨 Emergency Detection Model (Early Development)

### Input:
- Live or recorded speech, transcribed via automatic speech recognition (ASR).

### Output:
- Real-time alerts for threatening or dangerous phrases.

### Status:
- ✅ Dataset tagging for 10+ Indian languages is ongoing.
- 🔬 Exploring fine-tuned transformers or RNNs for multi-label classification.

### Planned Features:
- Works offline or in low-connectivity environments.
- Responds via Bluetooth earphones (TTS) or on-screen phonetic guide.

---

## 🔐 Data Collection Approach

- Audio data is collected through a **closed-source recording APK**.
- Contributors are **trusted individuals** fluent in native Indian languages.
- Data is:
  - **Anonymized**
  - **Stored securely** in Firebase Storage
  - Labeled with a consistent format: `sentence_language_gender.3gp`
- ❌ No sensitive speaker metadata is collected or shared.

---

## ⚠️ Why Code & Models Aren’t Public (Yet)

- Involves **real voices of private individuals** – releasing prematurely risks identity leakage.
- Models are undergoing **training, validation, and noise correction**.
- Public release will follow once a **sanitized version** is prepared.

---

## ✅ Future Roadmap

- 🗣️ Publish a sanitized version trained on synthetic or open-access voices.
- 📊 Release test datasets and evaluation scripts.
- 🌐 Provide a REST API for:
  - Pronunciation scoring
  - Emergency phrase detection
- 📄 Publish a research paper or technical whitepaper on findings.

---

## 📊 Current Progress

- [x] Sentence breakdown UI
- [x] Multi-language JSON structure
- [x] Firebase-secured recording app
- [🟡] Pronunciation model – **training ongoing**
- [🟡] Emergency classifier – **dataset prep in progress**
- [ ] Offline-first app integration
- [ ] Final TTS/STT deployment

---

## 📬 Contact

Want to contribute or test the model in your language?  
📧 **yadav.akanksha03@gmail.com**
