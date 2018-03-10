using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Aliens : MonoBehaviour {

	public float speed = 20;

	public Rigidbody2D rigidBody;

	public Sprite startingImage;
	public Sprite altImage;
	public Sprite explodedShipImage;

	private SpriteRenderer spriteRenderer;

	public float secBeforeSpriteChange = 0.5f;

	public GameObject alienBullet;

	public float minFireRateTime = 1.0f;
	public float maxFireRateTime = 3.0f;
	public float baseFireWaitTime = 3.0f;

	// Use this for initialization
	void Start () {
		rigidBody = GetComponent<Rigidbody2D> ();
		rigidBody.velocity = new Vector2 (1, 0) * speed;
		spriteRenderer = GetComponent<SpriteRenderer> ();
		StartCoroutine (changeAlienSprite ());
		baseFireWaitTime = baseFireWaitTime + Random.Range (minFireRateTime, maxFireRateTime);

	}

	void Turn(int direction){
		Vector2 newVelocity = rigidBody.velocity;
		newVelocity.x = speed * direction;
		rigidBody.velocity = newVelocity;
	}

	void MoveDown (){
		Vector2 position = transform.position;
		position.y -= 6;
		transform.position = position;
		speed = speed + 10;
	}

	void OnCollisionEnter2D(Collision2D col){
		if(col.gameObject.name == "LeftWall"){
			Turn (1);
			MoveDown ();
		}
		if(col.gameObject.name == "RightWall"){
			Turn (-1);
			MoveDown ();
		}
		if(col.gameObject.tag == "Bullet"){
			SpaceInvaderSoundManager.Instance.PlayOneShot (SpaceInvaderSoundManager.Instance.AlienDies);
			Destroy (gameObject);
		}
	}

	public IEnumerator changeAlienSprite(){
		while(true){
			if(spriteRenderer.sprite == startingImage){
				spriteRenderer.sprite = altImage;
				//SpaceInvaderSoundManager.Instance.PlayOneShot (SpaceInvaderSoundManager.Instance.AlienBuzz1);
			} else {
					spriteRenderer.sprite = startingImage;
				SpaceInvaderSoundManager.Instance.PlayOneShot (SpaceInvaderSoundManager.Instance.AlienBuzz2);
			}
			yield return new WaitForSeconds(0.5f);
		}
	}
	void FixedUpdate(){
		if(Time.time > baseFireWaitTime){
			baseFireWaitTime = baseFireWaitTime + Random.Range (minFireRateTime, maxFireRateTime);

			Instantiate(alienBullet, transform.position, Quaternion.identity);
		}
	}

	void OnTriggerEnter2D(Collider2D col){
		if(col.gameObject.tag=="Player"){
			SpaceInvaderSoundManager.Instance.PlayOneShot (SpaceInvaderSoundManager.Instance.ShipExplosion);
			col.GetComponent<SpriteRenderer> ().sprite = explodedShipImage;
			Destroy (gameObject);
			DestroyObject (col.gameObject, 0.5f);
		}
	}

}