## Overview

This document describes how to configure and run the MowItNowApplication.

This program performs a parsing and execution of a lawnmowing plan.

Expected input file format : 
<pre>
5 5
1 2 N
GAGAGAGAA
3 3 E
AADAADADDA
</pre>

## Prerequisites

- Java JDK 17
- Maven 3.9 or superior

## Building

1. First clone the repo : git clone https://github.com/DiazAntoine/solent-backend-test.git
2. Create a input.txt file and put it at the root of the project
3. Compile the project : mvn clean compile
4. Launch the application : mvn exec:java -Dexec.mainClass="com.diaz.mowitnow.MowItNowApplication" -Dexec.args="input.txt"

## What is missing 

- Implementing a Command Pattern for Action execution in MowingActionExecutor (debatable).
- Instead of returning a Mower, the MowingPlanExecutor could return a MowingResult.
- Should prevent the creation of a mower at coordinates already taken + should prevent a lawnmower from going to coordinates already taken.
