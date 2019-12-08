#! /bin/bash
jjtree BackEnd.jjt && javacc BackEnd.jj && javac *.java && java BackEnd test.ccal
