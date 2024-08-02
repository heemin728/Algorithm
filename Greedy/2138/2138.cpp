#include <algorithm>
#include <iostream>
#include <string>
#include <vector>
#define MAX 100001
using namespace std;

int main() {
  int N;
  cin >> N;

  string a, b;

  cin >> a >> b;

  vector<bool> start;
  vector<bool> start2;
  vector<bool> end;
  int count = 0;

  for (int i = 0; i < N; i++) {
    int num = a[i] - '0';
    int num2 = b[i] - '0';
    start.push_back(num);
    start2.push_back(num);
    end.push_back(num2);
  }

  for (int i = 1; i < N; i++) {
    if (start[i - 1] != end[i - 1]) {
      count++;

      start[i - 1] = !start[i - 1];
      start[i] = !start[i];
      if (i != N - 1) {
        start[i + 1] = !start[i + 1];
      }
    }
  }

  bool keep = true;

  for (int i = 0; i < N; i++) {
    if (start[i] != end[i]) {
      keep = false;
      break;
    }
  }
  if (keep) {
    cout << count << "\n";
    return 0;
  }

  int count2 = 1;
  start2[0] = !start2[0];
  start2[1] = !start2[1];


  for (int i = 1; i < N; i++) {
    if (start2[i - 1] != end[i - 1]) {
      count2++;

      start2[i - 1] = !start2[i - 1];
      start2[i] = !start2[i];
      if (i != N - 1) {
        start2[i + 1] = !start2[i + 1];
      }
    }

  }


  keep = true;
  for (int i = 0; i < N; i++) {
    if (start2[i] != end[i]) {
      keep = false;
      break;
    }
  }
  if (keep) {
    cout << count2 << "\n";
    return 0;
  }

  cout << "-1\n";
}