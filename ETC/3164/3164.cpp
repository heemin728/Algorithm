#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int main() {
  int x1, y1, x2, y2;
  cin >> x1 >> y1 >> x2 >> y2;

  long long cnt = 0;

  for (int x = x1 + 1; x <= x2; x++) {
    int y = y2;

    // 짝수인 경우
    if (x % 2 == 0) {
      if (x >= y) {
        cnt += (y - y1);
      }
      else{
        if (y % 2 == 1) {
          y--;
        }

        // 겹치는 경우
        if(x>y1+1){
            
            int diff=y-x;
            diff += 2 - (diff % 2);
            cnt+=(diff/2 + x-y1-1);
        }
        else{ // 겹치지 않는 경우
            int diff=y-y1-1;
            diff += 2 - (diff % 2);
            cnt+=(diff/2);
        }
      }
    }
    else { // 홀수인 경우
      if (x < y) {
        if (y % 2 == 1) {
          y--;
        }

        int diff=y-max(x+1,y1+1);
        diff += 2 - (diff % 2);
        cnt+=(diff/2);        
      }
    }
  }
  cout << cnt << "\n";
}