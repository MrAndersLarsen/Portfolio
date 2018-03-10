using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Coin : MonoBehaviour {


    void OnCollisionEnter2D(Collision2D collision)
    {
     
            SuperMarioSoundManager.Instance.PlayOneShot(SuperMarioSoundManager.Instance.getCoin);

            IncreaseTextUIScore();

        Destroy(gameObject);

        
    }

    void IncreaseTextUIScore()
    {
        var textUIComp = GameObject.Find("Score").GetComponent<Text>();
        int score = int.Parse(textUIComp.text);
        score += 10;
        textUIComp.text = score.ToString();
    }
}
