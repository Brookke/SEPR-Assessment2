Executable Test Plan
====================

Unit Tests
-----------

This project is tested using JUnit. Tests are located in the
``/game/tests`` directory. For test documentation, please see
https://github.com/junit-team/junit4/wiki

Running Unit Tests
~~~~~~~~~~~~~~~~~~~~~~~~~~

For every commit CircleCI runs all the included tests, however, we
recommend that you run tests locally too before committing.

We have included a handy test configuration inside the repository that can be run from
IntelliJ IDEA. |Running tests locally in intellij|

Adding Tests
~~~~~~~~~~~~

-  Create new class for tests under ``/game/tests/src`` When naming the
   class end the name with ``UnitTests`` for consistency e.g.
   ``PlayerUnitTests``
-  This class should extend ``GameTester`` this initialises the backend
   of the game so that tests run correctly.
-  Import ``org.junit.Test``
-  Write a test function using assertions, and use ``@Test`` decorator
   above it
-  See this page for examples of assertions:
   https://github.com/junit-team/junit4/wiki/assertions
-  Run your tests locally and see if they pass!

CircleCI Test Results
~~~~~~~~~~~~~~~~~~~~~

After tests have run the results are displayed in the “Test Summary” tab
on CircleCI.

If the tests have failed and no test summary is provided, this normally
means that the code doesn’t compile, or there is a problem with the test
code. To gather more information, scroll down to read the console output
from when the tests were run.

Other Tests
-----------

We’ve also included a bunch of test scripts that verify the game
performs as expected. These act as manual acceptance tests and these
have not been automated. These can be found in the Appendix for the
Assessment 2 documents.

.. |Running tests locally in intellij| image:: https://thumbs.gfycat.com/SentimentalGargantuanAmericanshorthair-size_restricted.gif