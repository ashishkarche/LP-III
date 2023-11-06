'''
Name :- Ashish Karche
Div-Roll No :- A-54
LP-III Assignment - 1

'''

# Method - 1 = Non-recursion
nterms = int(input("How many terms? \n"))

# First two terms
n1,n2 = 0, 1
count = 0

# Check if the number of terms is valid
if nterms <= 0:
    print("Please enter a positive integer")
# if there is only one term, return n1
elif nterms == 1:
    print("Fibonacci sequence upto ",nterms,":")
    print(n1)
# generate fibonacci sequence
else:
    print("Fibonacci sequence: ")
    while count < nterms:
        print(n1)
        nth = n1 + n2
        # update values
        n1 = n2
        n2 = nth
        count += 1

        
# Method - 2 = recursion
def recursion_fibonacci(n):
    if n <= 1:
        return n
    else:
        return(recursion_fibonacci(n-1)+recursion_fibonacci(n-2))
    
nterms = int(input("how many terms? \n"))

# check if the number of terms is valid
if nterms <= 0:
    print("Please enter a positive integer \n")
else:
    print("Fibonacci sequence: ")
    for i in range(nterms):
        print(recursion_fibonacci(i))