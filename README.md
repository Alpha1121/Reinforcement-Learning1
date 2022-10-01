# Reinforcement-Learning
All projects related to reinforcement learning

1. POMDP - Given the stochastic actions and stochastic observations of an agent, finds the probability distribution of the existence of the agent in different states of the given environment.

Uses the formula: b'(s') = (alpha) * P(e|s') * [P(s'|s1,a)*b(s1) + P(s'|s2,a)*b(s2) + ...] to update the belief states.

Here, 

- (alpha) represents the normalization factor.

- P(e|s') shows the probability of observing "e" when in state "s'"

- P(s'|s1,a) show the probability of reaching s' when taking action "a" in state s1

- and b(s1) is the belief state of the state "s1"

- Inside the summation: s1, s2 and so on are states that when taken an action in, can lead to state s'.

- The final grid looks something like this after taking actions: (UP, UP, UP), and observing (1 wall, 1 wall, 1 wall) respectively after each action.

0.0147 | 0.0409 | 0.7918 | 0.0
9.0E-4 | 0.0    | 0.1397 | 0.0
6.0E-4 | 0.0049 | 0.0058 | 7.0E-4
