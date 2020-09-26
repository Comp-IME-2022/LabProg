#   IME 2022 - LabProg II
#
#       The goal of this code is to train the use of python's string API

def function_return(name, value):
    if(value):
        return "O input é um " + name + "!"
    else:
        return "O input não é um " + name + "!"

def check_palindrome(word):
    word = word.lower()
    word = word.replace(' ', '')
    buffer = word[::-1]
    
    if(word == buffer):
        return function_return("Palíndromo", True)
    return function_return("Palíndromo", False)

def check_anagram(word1, word2):
    word1 = ''.join(sorted(word1.lower()))
    word2 = ''.join(sorted(word2.lower()))

    if(word1 == word2):
        return function_return("Anagrama", True)
    return function_return("Anagrama", False)

def check_panagram(word):
    word = ''.join(sorted(word.replace(' ', '').lower()))
    for i in range(0, len(word) - 1):
        if(ord(word[i+1])-ord(word[i]) > 1):
            return function_return("Panagrama", False)
    if(word[0]!='a' or word[len(word)-1]!='z'):
        return False
    
    return function_return("Panagrama", True)


print(check_palindrome("A torre da derrota"))
print(check_anagram("amor", "Roma"))
print(check_panagram("The quick brown fox jumps over the lazy dog"))
