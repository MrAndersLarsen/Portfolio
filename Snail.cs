using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Snail : MonoBehaviour {

    public float speed = 2f;
    Vector2 direction = Vector2.right;
    void FixedUpdate()
    {
        GetComponent<Rigidbody2D>().velocity = direction*speed ;    
    }

    private void OnTriggerEnter2D(Collider2D collision)
    {
        transform.localScale = new Vector2(-1 * transform.localScale.x, transform.localScale.y);
        direction = new Vector2(-1 * direction.x, direction.y);
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.name == "Manny")
        {
            if (collision.contacts[0].point.y > transform.position.y)
            {
                GetComponent<Animator>().SetTrigger("Dead");
                GetComponent <Collider2D>().enabled = false;
                direction = new Vector2(direction.x, -1);
                DestroyObject(gameObject, 3);
            }
            else
            {
                SuperMarioSoundManager.Instance.PlayOneShot(SuperMarioSoundManager.Instance.mannyDies);
                DestroyObject(collision.gameObject, .5f);
            }
        }
    }

}
