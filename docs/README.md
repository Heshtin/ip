# Augustus User Guide

Greetings, citizen.

Augustus is an **imperial-themed task manager** for keeping your duties,
deadlines, and events under control. Rome was not built in a day, and
neither is your assignment — so you may as well organise it.

Issue commands, organise your tasks, and let Augustus keep the empire —
or at least your schedule — in order.


## Quick Start

1. Ensure that Java `25` or later is installed on your computer.
2. Launch Augustus using the JAR file.
3. The Augustus GUI should appear.
4. Type a command into the command box and press **Enter** or click **Send**.
5. Refer to the command summary and features below for the available commands.

Some commands you can try:

- `todo Review lecture notes`
- `deadline Submit report /by 2026-09-25`
- `event Team meeting /from 2pm /to 4pm`
- `list`

---

## Features

**Notes about the command format:**

- Words in `UPPER_CASE` are values that you need to provide.
  For example, in `todo DESCRIPTION`, replace `DESCRIPTION` with a task such
  as `Review lecture notes`.
- `NUMBER` refers to the task number shown when using the `list` command.
- Dates for deadlines must use the `yyyy-MM-dd` format.
  For example, `2026-09-25`.

### Adding a todo: `todo`

Adds a task without a date or time.

Format: `todo DESCRIPTION`

Example:

`todo Review lecture notes`

Expected outcome:

```text
By my decree, this task shall be recorded:
[T][ ] Review lecture notes
```

### Adding a deadline: `deadline`

Adds a task that must be completed by a specified date.

Format: `deadline DESCRIPTION /by DATE`

The date must use the `yyyy-MM-dd` format.

Example:

`deadline Submit report /by 2026-09-25`

Expected outcome:

```text
By my decree, this task shall be recorded:
[D][ ] Submit report (by: Sep 25 2026)
```

### Adding an event: `event`

Adds an event with a start and end time.

Format: `event DESCRIPTION /from START /to END`

Example:

`event Team meeting /from 2pm /to 4pm`

Expected outcome:

```text
By my decree, this task shall be recorded:
[E][ ] Team meeting (From: 2pm to: 4pm)
```

### Listing all tasks: `list`

Shows all tasks recorded in Augustus.

Format: `list`

Example:

`list`

Example output:

```text
Behold the tasks recorded under my rule:
1. [T][ ] Review lecture notes
2. [E][ ] Team meeting (From: 2pm to: 4pm)
3. [D][ ] Submit report (by: Sep 25 2026)
```

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Example:

`find report`

Augustus displays tasks that match the keyword.

### Marking a task as completed: `mark`

Marks the specified task as completed.

Format: `mark NUMBER`

- `NUMBER` refers to the task number shown by `list`.
- The task number must be valid.

Example:

`mark 1`

Expected outcome:

```text
Well done. I declare this task complete:
[T][X] Review lecture notes
```

### Marking a task as not completed: `unmark`

Marks a completed task as incomplete.

Format: `unmark NUMBER`

Example:

`unmark 1`

Expected outcome:

```text
So be it. This task returns to active duty:
[T][ ] Review lecture notes
```

### Deleting a task: `delete`

Removes the specified task from Augustus.

Format: `delete NUMBER`

- `NUMBER` refers to the task number shown by `list`.
- Deleting a task does not affect the other tasks.

Example:

`delete 1`

Expected outcome:

```text
By my order, this task has been removed from the records:
[T][ ] Review lecture notes
```

### Tagging a task: `tag`

Assigns a tag to a task to help organise it.

Format: `tag NUMBER /t TAG`

Example:

`tag 1 /t university`

Expected outcome:

```text
This task shall bear the following mark:
[T][ ] Review lecture notes [#university]
```

A task can have one tag at a time. Assigning another tag replaces its
existing tag.

### Exiting Augustus: `bye`

Closes the application.

Format: `bye`

Expected outcome:

```text
You are dismissed.
May your duties be completed with honour.
```

---

## Command Summary

| Action | Format | Example |
| --- | --- | --- |
| Add todo | `todo DESCRIPTION` | `todo Review lecture notes` |
| Add deadline | `deadline DESCRIPTION /by DATE` | `deadline Submit report /by 2026-09-25` |
| Add event | `event DESCRIPTION /from START /to END` | `event Team meeting /from 2pm /to 4pm` |
| List tasks | `list` | `list` |
| Find tasks | `find KEYWORD` | `find report` |
| Mark task | `mark NUMBER` | `mark 1` |
| Unmark task | `unmark NUMBER` | `unmark 1` |
| Delete task | `delete NUMBER` | `delete 1` |
| Tag task | `tag NUMBER /t TAG` | `tag 1 /t university` |
| Exit | `bye` | `bye` |

---

## Saving Data

Augustus automatically saves your tasks to the file
`data/augustus.txt` whenever the task list is updated.

You do not need to save your tasks manually. When Augustus starts again,
it loads the saved tasks from this file so that your previous data is
restored.