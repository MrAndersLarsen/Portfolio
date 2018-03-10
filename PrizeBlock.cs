using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class PrizeBlock : MonoBehaviour {

    public AnimationCurve anim;
    public int coinInBlock = 5;
    void OnCollisionEnter2D(Collision2D collision)
    {
        if(collision.contacts[0].point.y < transform.position.y)
        {
            StartCoroutine(RunAnimation());
        }    
        if(coinInBlock >= 0)
        {
            SuperMarioSoundManager.Instance.PlayOneShot(SuperMarioSoundManager.Instance.getCoin);

            IncreaseTextUIScore();

            coinInBlock--;
        }
    }

    IEnumerator RunAnimation()
    {
        Vector2 startPos = transform.position;
        for(float x = 0; x < anim.keys[anim.length-1].time; x += Time.deltaTime)
        {
            transform.position = new Vector2(startPos.x, startPos.y + anim.Evaluate(x));

            yield return null;
        }
    }

    void IncreaseTextUIScore()
    {
        var textUIComp = GameObject.Find("Score").GetComponent<Text>();
        int score = int.Parse(textUIComp.text);
        score += 10;
        textUIComp.text = score.ToString();
    }
}
