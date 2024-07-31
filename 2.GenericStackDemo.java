import java.util.ArrayList;
import java.util.List;

class GenericStack<T> {
    private List<T> stack;

    public GenericStack() {
        stack = new ArrayList<>();
    }

    public void push(T element) {
        stack.add(element);
    }

    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public void clear() {
        stack.clear();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
        } else {
            System.out.println("Stack elements:");
            for (T element : stack) {
                System.out.println(element);
            }
        }
    }
}

public class GenericStackDemo {
    public static void main(String[] args) {
        
        GenericStack<String> stringStack = new GenericStack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Generic");
        stringStack.push("Stack");
        
        System.out.println("String Stack:");
        stringStack.display();
        
        System.out.println("Popped element: " + stringStack.pop());
        stringStack.display();
        stringStack.clear();
        stringStack.display();
        
        
        GenericStack<Integer> integerStack = new GenericStack<>();
        integerStack.push(10);
        integerStack.push(20);
        integerStack.push(30);
        integerStack.push(40);
        
        System.out.println("\nInteger Stack:");
        integerStack.display();
        
        System.out.println("Popped element: " + integerStack.pop());
        integerStack.display();
        integerStack.clear();
        integerStack.display();
    }
}
