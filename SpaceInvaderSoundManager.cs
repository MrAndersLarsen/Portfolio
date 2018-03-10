using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpaceInvaderSoundManager : MonoBehaviour {

	public static SpaceInvaderSoundManager Instance = null;

	public AudioClip Laser1;
	public AudioClip Laser2;
	public AudioClip Laser3;
	public AudioClip SmallExplosion;
	public AudioClip ShipExplosion;
	public AudioClip AlienBuzz1;
	public AudioClip AlienBuzz2;
	public AudioClip AlienDies;


	private AudioSource soundEffectAudio;

	// Use this for initialization
	void Start () {
		if(Instance == null){
			Instance = this;
		} else if (Instance != this){
			Destroy (gameObject);
		}

		AudioSource theSource = GetComponent<AudioSource> ();
		soundEffectAudio = theSource;

	}

	public void PlayOneShot(AudioClip clip){
		soundEffectAudio.PlayOneShot (clip);
	}
}