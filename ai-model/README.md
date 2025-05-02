# AI Model – Phonetic Accuracy & Emergency Detection (In Progress)

This folder outlines the AI model pipeline being developed for the LangLearn project.  
The model is currently not included for privacy and data protection reasons.

---

## 🎯 Objective

The AI model has two primary goals:

1. Phonetic Pronunciation Scoring  
   Evaluate user pronunciation of emergency sentences in Indian languages with at least 75% phoneme-level accuracy.

2. Emergency Phrase Detection  
   Detect potential verbal threats or distress in multiple Indian languages to assist users in unfamiliar or hostile situations.

---

## 🧱 Architecture (Planned)

### 🗣️ Pronunciation Grading Model

- Input: Audio recordings from user
- Pipeline:
  - Convert audio to MFCC features
  - Align with target sentence phoneme map
  - Compare timing, stress, and frequency match
- Approach:
  - Custom scoring model inspired by Kaldi + DeepSpeech
  - Focus on low-resource Indian languages
- Libraries: TensorFlow, librosa, phonemizer, Wav2Vec2 (optional)

### 🚨 Emergency Detection Model

- Input: Real-time speech (converted to text via ASR)
- Detection: Multi-label classifier trained on tagged phrases in 10+ Indian languages
- Model:
  - Fine-tuned transformer or RNN model
  - Optional real-time NLP threat scanning
- Use Case:
  - Alerts if user says or hears potentially dangerous phrases
  - Works offline or in low-connectivity areas

---

## 🔐 Data Collection Approach

- Voice recordings are collected via a closed-source recording app
- Contributors are trusted individuals fluent in native Indian languages
- All data is anonymized and securely stored in Firebase Storage
- No sensitive audio or speaker metadata is shared

---

## ⚠️ Why Code & Model Aren't Public (Yet)

- Audio data includes real voices of private individuals
- Public release may risk identity leakage or misuse
- Model weights are still under training, validation, and noise correction

---

## ✅ Future Plan

- Publish a sanitized version of the model with synthetic voice training
- Open-source test datasets and evaluation scripts
- Provide a REST API for pronunciation scoring and emergency phrase detection
- Publish a research paper or technical whitepaper on findings

---

## 🧠 Status: Work in Progress

- [x] Sentence tokenization for 10 Indian languages
- [x] Audio preprocessing pipeline (MFCC, silence removal)
- [x] Firebase-secured recording pipeline
- [ ] Pronunciation scoring model (under training)
- [ ] Threat detection NLP classifier
- [ ] Offline inference engine (planned for integration)

---

## 📬 Contact

Want to collaborate or help test the model in your language?  
Reach out at [yadav.akanksha03@gmail.com]

