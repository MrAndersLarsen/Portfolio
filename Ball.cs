using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Ball : MonoBehaviour {

	public float speed = 30;

	public void ResetSpeed(){
		speed = 30;
	}

	public void BallStart(){
		rigidBody.velocity = Vector2.right * speed;
	}

	public void ResetBall(){
		Vector2 dir = new Vector2 ();
		transform.position = new Vector2 (0, 0);
		rigidBody.velocity = dir * 0;
		Invoke ("ResetSpeed", 1);
		Invoke ("BallStart", 2);
	
	}

	void DestroyGameObject(){
		Destroy (gameObject);
	}

	private Rigidbody2D rigidBody;

	private AudioSource audioSource;

	// Use this for initialization
	void Start () {

		rigidBody = GetComponent<Rigidbody2D> ();	
		BallStart ();

	}

	void OnCollisionEnter2D(Collision2D col){

		// Player Paddle or Computer Paddle
		if ((col.gameObject.name == "Player Paddle") ||
			(col.gameObject.name == "Computer Paddle")) {

			HandlePaddleHit (col);
			speed=speed+4;


		}

		// Bottom Wall or Top Wall
		if ((col.gameObject.name == "Bottom Wall") ||
			(col.gameObject.name == "Top Wall")) {

			PongSoundManager.Instance.PlayOneShot (PongSoundManager.Instance.WallHit);

		}

		// Left Wall or Right Wall
		if ((col.gameObject.name == "Left Wall") ||
			(col.gameObject.name == "Right Wall")) {
			// TODO Update Score UI
			if(col.gameObject.name == "Left Wall"){
				PongSoundManager.Instance.PlayOneShot (PongSoundManager.Instance.FailSound);
				IncreaseTextUIScore ("RightScoreUI");
			} 

			if(col.gameObject.name == "Right Wall"){
				PongSoundManager.Instance.PlayOneShot (PongSoundManager.Instance.WinSound);
				IncreaseTextUIScore ("LeftScoreUI");
			} 
				
			ResetBall ();

		}
	}



	void HandlePaddleHit(Collision2D col){
		float y = BallHitPaddleWhere (transform.position,
			          col.transform.position, col.collider.bounds.size.y);

		Vector2 dir = new Vector2 ();

		if(col.gameObject.name == "Player Paddle"){
			dir = new Vector2 (1, y).normalized;
		}

		if(col.gameObject.name == "Computer Paddle"){
			dir = new Vector2 (-1, y).normalized;
		}

		rigidBody.velocity = dir * speed;

		PongSoundManager.Instance.PlayOneShot (PongSoundManager.Instance.PlayerPaddleHit);

	}

	float BallHitPaddleWhere(Vector2 ball, Vector2 paddle, float paddleHeight){
		return (ball.y - paddle.y) / paddleHeight;
	}

	void IncreaseTextUIScore(string textUIName){
		var textUIComp = GameObject.Find (textUIName).GetComponent<Text> ();

		int score = int.Parse (textUIComp.text);
		score++;
		textUIComp.text = score.ToString ();
	}
}