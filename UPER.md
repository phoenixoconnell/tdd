#The Problem Solving Framework : 'UPER'

* U = "Understand"
* P = "Plan"
* E = "Execute"
* R = "Reflect" / "Refactor"

##1. Understanding the Problem
* Given a list of items that one might find in a store, calculate the total bill including sales tax with respect to import and exemption rules.
* Use test-driven development to develop the solution.
* Refactor tests and code as needed while developing to obtain desired solution.

##2. Planning the Solution
* Divide problem into base components and create classes for them.
* Discern likely needed methods in classes and create stubs with expected signatures.
* Write tests using stub methods with expected values that mirror the provided solution output.
* Fill in stubs with initial full methods.
* Run tests.

##3. Executing the Plan
* Solution required an item class for individual store items, and a register class which runs through a "cart" of items.
* Wrote item and register classes with method stubs.
* Wrote item class tests expecting item to hold data and have getters for data, with no other methods or calculations.
* Wrote register class tests expecting register to perform all calculations and return a generated receipt.
* Tested initial build of classes with failing results (outputs for all monetary values, while being calculated correctly, were not outputting the correct expected values due to rounding and formatting issues).
* Researched rounding methods and decided that multiplying the value by 20, rounding up to the nearest integer, and then dividing by 20 was the best way to round to the nearest $0.05.
* Researched formatting options and decided that NumberFormat.getCurrencyInstance().format() was the best way forward.
* Included decisions in code and reran tests successfully.

##4. Reflection / Refactor
* Upon reviewing code, realized checkout method in register class was repeating many lines of similar code and made decision to refactor.
* Created new method in item class to calculate tax for a particular item based on a passed in tax rate.
* Wrote test for stub.
* Created another method in item class that rounds to the nearest $0.05 for any amount passed in and returns the updated amount.
* Wrote test for stub.
* Filled in stub methods.
* Ran tests for new methods and updated checkout code with new methods.
* Created new method for register that creates a pre-tax subtotal for an item in a cart.
* Created new method for correctly formatting monetary values in output.
* Wrote tests for stub methods.
* Filled in stub methods.
* Ran tests for updated methods and updated checkout code with new methods.
