package com.swap.utility;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;

public class InputManager {

	private static Stack<InputProcessor> inputStack = new Stack<InputProcessor>();
	
	public static void addInputProcessor(InputProcessor processor) {
		// Set the input processor to the new one
		Gdx.input.setInputProcessor(processor);
		// Add it to the stack
		inputStack.push(processor);
	}
	
	public static void removeInputProcessor() {
		// Remove the top processor from the stack
		inputStack.pop();
		
		// Set the input processor to the top of the stack
		Gdx.input.setInputProcessor(inputStack.peek());
		
	}
	
}
