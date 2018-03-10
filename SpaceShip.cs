using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpaceShip : MonoBehaviour {

	public float speed = 30;

	public float timeBetweenShots = 0.1f;

	private float timestamp;

	public GameObject PlayerBullets;

	void FixedUpdate(){
		float horzMove = Input.GetAxisRaw ("Horizontal");
		GetComponent<Rigidbody2D> ().velocity = new Vector2 (horzMove, 0) * speed;
	}
	// Update is called once per frame
	void Update () {
	
		if(Time.time >= timestamp && (Input.GetButtonDown("Jump"))){
			Instantiate (PlayerBullets, transform.position, Quaternion.identity);
			SpaceInvaderSoundManager.Instance.PlayOneShot (SpaceInvaderSoundManager.Instance.Laser1);
			timestamp = Time.time + timeBetweenShots;
		}

	}
}
