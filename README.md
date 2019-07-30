# CPSC 110 Lab Roster Generator
&copy; 2018-2019 Kean Wah Wong. Licensed under MIT License

### What is this? And what does it do?
The CPSC 110 Lab Roster Generator is a Java desktop application that takes in a CSV file (with student information), downloaded from Canvas,
and allows TAs to choose their lab section. The program will then generate a lab roster in .XLS form.

This reduces the need to have to manually re-make the lab roster, especially before the Add/Drop/W deadline. I developed this
application because I was sick of doing that (although you will still have to redownload one CSV file).

### What does this application ***NOT*** do?
This Lab Roster Generator, unfortunately, does not:

- Automatically download the CSV file. You'll have to download that yourself, and tell the program where it is.
- Upload the grades to Canvas. You'll still have to do that yourself. I don't think there is a way around this without risking overriding other grades, but I might be wrong.

I'd love to try and get these things working once I have the time, but my resources are limited especially because I no longer TA 110.

### Something doesn't work. Help!
Please open an issue describing your problem and I'll see what I can do to help. Lab formats may change (grading schemes, etc),
as may the data format.

### Possible future enhancements
- Automatically download CSV file upon program load (Canvas API?)

### Want to take up this project?
I'm no longer a 110 TA, so I might not be the best person to enhance this. If you'd like to take over this project, feel free to fork it and make changes as you see fit!

### License
Copyright &copy; 2018-2019 Kean Wah Wong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
