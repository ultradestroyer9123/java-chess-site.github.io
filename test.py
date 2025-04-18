s = "rrrbbb   ceee"

letterRepeat = 1
letterRepeating = s[0]
finalString = ""

for i in range(1, len(s) + 1):
    if i < len(s) and s[i] == letterRepeating:
        letterRepeat += 1
    else:
        if letterRepeat > 2:
            finalString += letterRepeating + str(letterRepeat)
        else:
            finalString += s[i - letterRepeat:i]
        if i < len(s):
            letterRepeating = s[i]
            letterRepeat = 1

print(finalString)
