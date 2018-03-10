using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class GameController : MonoBehaviour {

    public int maxSize;
    public int currentSize;
    public int xBound;
    public int yBound;
    public GameObject foodPreFab;
    public GameObject currentFood;
    public GameObject snakePreFab;
    public Snake head;
    public Snake tail;
    public int NESW;
    public Vector2 nextPos;
    public int score;
    public Text scoreText;
    public float deltaTimer;

    void OnEnable()
    {
        Snake.hit += hit;
    }

    void OnDisable()
    {
        Snake.hit -= hit;
    }

    private void Start()
    {
        InvokeRepeating("TimerInvoke", 0, deltaTimer);
        FoodFunction();
    }

    void TimerInvoke()
    {
        Movement();
        StartCoroutine(CheckVisable());
        if(currentSize >= maxSize)
        {
            TailFunction();
        }
        else
        {
            currentSize++;
        }
    }

    private void Update()
    {
        ChangeDirection();
    }

    void Movement()
    {
        GameObject temp;
        nextPos = head.transform.position;

        switch (NESW)
        {
            case 0:
                nextPos = new Vector2(nextPos.x, nextPos.y + 1);
                break;
            case 1:
                nextPos = new Vector2(nextPos.x + 1, nextPos.y);
                break;
            case 2:
                nextPos = new Vector2(nextPos.x, nextPos.y - 1);
                break;
            case 3:
                nextPos = new Vector2(nextPos.x - 1, nextPos.y);
                break;
        }

        temp = (GameObject)Instantiate(snakePreFab, nextPos, transform.rotation);

        head.SetNext(temp.GetComponent<Snake>());
        head = temp.GetComponent<Snake>();
        return;
    }

    void ChangeDirection()
    {
        if(NESW != 2 && Input.GetKeyDown(KeyCode.W))
        {
            NESW = 0;
        }
        if (NESW != 3 && Input.GetKeyDown(KeyCode.D))
        {
            NESW = 1;
        }
        if (NESW != 0 && Input.GetKeyDown(KeyCode.S))
        {
            NESW = 2;
        }
        if (NESW != 1 && Input.GetKeyDown(KeyCode.A))
        {
            NESW = 3;
        }
    }
    void TailFunction()
    {
        Snake tempSnake = tail;
        tail = tail.GetNext();
        tempSnake.RemoveTail();
    }

    void FoodFunction()
    {
        int xPos = Random.Range(-xBound, xBound);
        int yPos = Random.Range(-yBound, yBound);
        currentFood = (GameObject)Instantiate(foodPreFab, new Vector2(xPos, yPos), transform.rotation);
        StartCoroutine(CheckRender(currentFood));
    }

    IEnumerator CheckRender(GameObject IN)
    {
        yield return new WaitForEndOfFrame();
        if(IN.GetComponent<Renderer>().isVisible == false)
        {
            if (IN.tag == "Food")
            {
                Destroy(IN);
                FoodFunction();
            }
        }
    }

    void hit(string WhatWasSent)
    {
        if(WhatWasSent == "Food")
        {
            if(deltaTimer >= 0.1f)
            {
                deltaTimer -= .025f;
                CancelInvoke("TimerInvoke");
                InvokeRepeating("TimerInvoke", 0, deltaTimer);
            }
            FoodFunction();
            maxSize++;
            score++;
            scoreText.text = score.ToString();
        }
       if(WhatWasSent == "Snake")
        {
            CancelInvoke("TimerInvoke");
            Exit();
        }
        if (WhatWasSent == "Wall")
        {

        }
    }
    public void Exit()
    {
        SceneManager.LoadScene(5);
    }

    void wrap()
    {
        if (NESW == 0)
        {
            head.transform.position = new Vector2(head.transform.position.x, -(head.transform.position.y - 1));
        }
        else if (NESW == 1)
        {
            head.transform.position = new Vector2(-(head.transform.position.x -1), head.transform.position.y);
        }
        else if (NESW == 2)
        {
            head.transform.position = new Vector2(head.transform.position.x, -(head.transform.position.y + 1));   
        }
        else if (NESW == 3)
        {
            head.transform.position = new Vector2(-(head.transform.position.x + 1), head.transform.position.y);
        }
    }
    IEnumerator CheckVisable()
    {
        yield return new WaitForEndOfFrame();
        if (head.GetComponent<Renderer>().isVisible == false)
        {
            wrap();
        }
    }
}
