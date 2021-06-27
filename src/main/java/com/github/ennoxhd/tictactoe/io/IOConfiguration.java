package com.github.ennoxhd.tictactoe.io;

import java.io.InputStream;
import java.io.OutputStream;

public record IOConfiguration(
		InputStream in,
		OutputStream out) {

	public IOConfiguration {
		if(in == null || out == null)
			throw new IllegalArgumentException("In and out must not be null.");
	}
}
