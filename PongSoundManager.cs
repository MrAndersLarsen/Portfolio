using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PongSoundManager : MonoBehaviour {

	public static PongSoundManager Instance = null;

	//Sounds used in the game
	public AudioClip Boing;
	public AudioClip PlayerPaddleHit;
	public AudioClip WallHit;
	public AudioClip FailSound;
	public AudioClip WinSound;

	private AudioSource soundEffectAudio;

	// Use this for initialization
	void Start () {

		if (Instance == null) {
			Instance = this;
		} else if (Instance != this) {
			Destroy (gameObject);
		}

		AudioSource[] sources = GetComponents<AudioSource> ();

		foreach (AudioSource source in sources) {
			if (source.clip == null) {
				soundEffectAudio = source;
			}
		}

	}

	public void PlayOneShot(AudioClip clip){
		soundEffectAudio.PlayOneShot (clip);
	}
}