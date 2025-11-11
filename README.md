# Adrian Garcia SE333 Assignment 6

Using Playwright manually for the first time was a learning process. Since I had never used it before, I relied a lot on
trial and error to understand how to navigate the browser, locate elements, and write proper assertions in Java. Once 
I got used to it, it felt pretty simple and straightforward, but it was also time-consuming. Every step needed to be 
written in the correct order, and even small mistakes could cause later parts of the test to fail. I realized that 
creating long tests manually could take up a lot of time since everything has to be sequenced carefully to make sure it 
all works as expected.

When I moved on to the AI-assisted method with the Playwright MCP in VS Code, the experience felt different. The hardest
part was setting up the MCP in the first place. It took some troubleshooting to get it installed correctly 
and working inside VS Code. Once it was running it became much smoother. I could describe the workflow in plain
language, and the agent would generate the test automatically. The main challenge was making sure my prompts were 
specific enough because if I left out details, the agent might skip steps or make incorrect assumptions. When the 
instructions were clear, it followed them well and produced accurate, runnable Java tests.

Comparing the two, the AI-assisted method was definitely faster to write once everything was working. It saved me from
writing repetitive code and locating every selector manually. However, it also required more patience, since the agent 
sometimes took a long time to think or test different approaches. The manually written tests took more effort but gave 
me full control and understanding of what was happening. The AI-generated ones were easier to create and maintain but 
needed careful prompting and review to make sure everything worked as intended. Overall, I can see the benefit of both.
Manual testing helps build technical understanding and precision, while AI-assisted testing saves time and allows for 
quick generation of complex workflows with less direct coding.