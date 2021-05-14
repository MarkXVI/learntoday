package Functionality;


import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.beans.PropertyVetoException;
import java.util.Locale;


public class TextToSpeech {

    SynthesizerModeDesc desc;
    Synthesizer synthesizer;
    javax.speech.synthesis.Voice voice;

    public TextToSpeech(String text) throws EngineException, AudioException, EngineStateError, PropertyVetoException, InterruptedException {
        if (desc == null) {
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            desc = new SynthesizerModeDesc(Locale.US);
            Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
            synthesizer = Central.createSynthesizer(desc);
            synthesizer.allocate();
            synthesizer.resume();
            SynthesizerModeDesc smd = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
            javax.speech.synthesis.Voice[] voices = smd.getVoices();
            for (Voice voice1 : voices) {
                if (voice1.getName().equals("kevin16")) {
                    voice = voice1;
                    break;
                }
            }
            synthesizer.getSynthesizerProperties().setVoice(voice);
            ReadText(text);
        }
    }

    public void Terminate() throws EngineException, EngineStateError {
        synthesizer.deallocate();
    }

    public void ReadText(String text) throws IllegalArgumentException, InterruptedException {
        synthesizer.speakPlainText(text, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
    }
}