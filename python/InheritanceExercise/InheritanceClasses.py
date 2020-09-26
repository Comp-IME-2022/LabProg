#   IME 2022 - LabProg II
#
#       The goal of this code is to implement inheritance on python classes.
#       This code also show the use of __str__ to modify the convertion to strings on classes.
#
#           -Mathematics is the superclass

class Mathematics:
    #Define just the prototype
    algorithm = ''
    def recursive(self, n):
        return None

    def __init__(self, n):
        self.n = n

    def calculate(self):
        return self.recursive(self.n)

    def __str__(self):
        return self.algorithm +  " de " + str(self.n) + " vale " + str(self.calculate())


class CalculoFibonacci(Mathematics):
    
    def __init__(self, n):
        super().__init__(n)
        self.algorithm = "Fibonacci"

    def recursive(self, i):
        if i==1 or i==0:
            return i
        else:
            return self.recursive(i-1) + self.recursive(i-2)

class CalculoSomaNumerosNaturais(Mathematics):

    def __init__(self, n):
        super().__init__(n)
        self.algorithm = "Fatorial"


    def recursive(self, i):
        if i==0:
            return i
        else:
            return i + self.recursive(i-1)
        

class CalculoFatorial(Mathematics):

    def __init__(self, n):
        super().__init__(n)
        self.algorithm = "Soma dos N primeiros números naturais"


    def recursive(self, i):
        if i==1 or i==0:
            return 1
        else:
            return i * self.recursive(i-1)
