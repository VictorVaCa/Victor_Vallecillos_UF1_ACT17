package com.example.victor_vallecillos_uf1_act17

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private var currentPlayer = 0 // 0 para Jugador 1 (X), 1 para Jugador 2 (O)
    private var gameBoard = IntArray(9) { 2 } // 2 para vacío, 0 para X, 1 para O

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttons = arrayOf(
            findViewById(R.id.button1),
            findViewById(R.id.button2),
            findViewById(R.id.button3),
            findViewById(R.id.button4),
            findViewById(R.id.button5),
            findViewById(R.id.button6),
            findViewById(R.id.button7),
            findViewById(R.id.button8),
            findViewById(R.id.button9)
        )

        for (i in buttons.indices) {
            buttons[i].tag = i
            buttons[i].setOnClickListener { view ->
                onClickButton(view as Button)
            }
        }
    }

    private fun onClickButton(button: Button) {
        val tag = button.tag as Int
        if (gameBoard[tag] == 2) {
            gameBoard[tag] = currentPlayer
            button.text = if (currentPlayer == 0) "X" else "O"
            if (checkWin()) {
                Toast.makeText(this, "Jugador ${if (currentPlayer == 0) "1 (X)" else "2 (O)"} gana!", Toast.LENGTH_SHORT).show()
                resetGame()
            } else if (checkDraw()) {
                Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show()
                resetGame()
            } else {
                currentPlayer = 1 - currentPlayer
            }
        }
    }

    private fun checkWin(): Boolean {
        val winConditions = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        for (condition in winConditions) {
            if (gameBoard[condition[0]] != 2 && gameBoard[condition[0]] == gameBoard[condition[1]] && gameBoard[condition[0]] == gameBoard[condition[2]]) {
                return true
            }
        }
        return false
    }

    private fun checkDraw(): Boolean {
        for (cell in gameBoard) {
            if (cell == 2) {
                return false
            }
        }
        return true
    }

    private fun resetGame() {
        currentPlayer = 0
        gameBoard = IntArray(9) { 2 }
        for (button in buttons) {
            button.text = ""
        }
    }
}