using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BrickBlock : MonoBehaviour {

    private SpriteRenderer sr;
    public Sprite explodedBlock;
    public float secondsBeforeSpriteChange = .2f;

    private void Awake()
    {
        sr = GetComponent<SpriteRenderer>();
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.contacts[0].point.y < transform.position.y)
        {
            SuperMarioSoundManager.Instance.PlayOneShot(SuperMarioSoundManager.Instance.rockSmash);
            sr.sprite = explodedBlock;
            DestroyObject(gameObject, secondsBeforeSpriteChange);
        }
    }


    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}
}
