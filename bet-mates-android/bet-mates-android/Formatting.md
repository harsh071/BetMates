# Java Code Style

#### 1. Braces
* Braces are used with **if**, **else**, **for**, **do** and **while** statement, even when the body is **empty** or contains only a single statement.
* Braces follow the Kernighan and Ritchie style ["Egyptian brackets"](http://www.codinghorror.com/blog/2012/07/new-programming-jargon.html) for **nonempty** blocks and block-like constructs:
    - No line break before the opening brace.
    - Line break after the opening brace.
    - Line break before the closing brace.
    - Line break after the closing brace, only if that brace terminates a statement or terminates the body of a method, constructor, or named class. For example, there is no line break after the brace if it is followed by else or a comma.
    * ***Example***:
    ```java
    return new MyClass() {
      @Override public void method() {
        if (condition()) {
          try {
            something();
          } catch (ProblemException e) {
            recover();
          }
        } else if (otherCondition()) {
          somethingElse();
        } else {
          lastThing();
        }
      }
    };
    ```
    
* An **empty block**. Alternatively, it may be closed immediately after it is opened, with no characters or line break in between ({}), **unless** it is part of a multi-block statement (one that directly contains multiple blocks: if/else or try/catch/finally).
    * ***Example***:
    ```java
    // This is acceptable
  void doNothing() {}
  
    // This is equally acceptable
    void doNothingElse() {
    }
    ```
    * This is **not** acceptable: No concise empty block in a multi-block statement
    ```java
    try {
      doSomething();
  } catch (Exception e) {}
    ```

#### 2. Comments
* Block comment style
    * Block comments are indented at the same level as the surrounding code. They may be in ```/* ... */``` style or ```// ...``` style. For multi-line ```/* ... */``` comments, subsequent lines must start with __*__ aligned with the __*__ on the previous line.
    * ***Example***:
    ```java
    /*
     * This is          // And so           /* Or you can
     * okay.            // is this.          * even do this. */
     */
    ```

#### 3. Naming
* __Calss names__ are written in __UpperCamelCase__.
* __Method names__ are written in __lowerCamelCase__.
  * For unittest, may use underscores __\<methodUnderTest\>_\<state\>__, for example __pop_emptyStack__.

* __Constant__ name using __all__ uppercase letters, may use underscores to separate words.
* __Parameter__ names and __local variable__ names use __lowerCamelCase__

#### 4. Javadoc
* The basic formatting fo Javadoc blocks is as seen in this example:

```java
  /**
   * Multiple lines of Javadoc text are written here,
   * wrapped normally...
   */
  public int method(String p1) { ... }

  /** An especially short bit of Javadoc. */
```



---
This document is based on Google Java Style Guide, for detail please refer to [link here](https://google.github.io/styleguide/javaguide.html)
=======
*


---
For detail please refer to [link here](https://google.github.io/styleguide/javaguide.html)
