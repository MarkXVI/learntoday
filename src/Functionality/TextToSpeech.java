package Functionality;


import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;
import java.util.Locale;


public class TextToSpeech implements Runnable {

    SynthesizerModeDesc desc;
    Synthesizer synthesizer;
    javax.speech.synthesis.Voice voice;

    String text;

    Thread Read;

    public TextToSpeech() {
        try {
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
                Read = new Thread(this, "ReadThread");
                Read.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Terminate() throws EngineException, EngineStateError {
        synthesizer.deallocate();
    }

    private synchronized void ReadText() throws IllegalArgumentException, InterruptedException {
        synthesizer.speakPlainText(text, null);
        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
    }

    @Override
    public void run() {
        try {
            ReadText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    public void pause() throws InterruptedException {
        Thread.currentThread().wait();
        System.out.println("Paused");
    }

    public void stop() throws InterruptedException {
        synthesizer.cancel();
    }
}